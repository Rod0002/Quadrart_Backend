package com.quadrart.Controllers.QuadroController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.quadrart.Services.QuadroService.QuadroService;
import com.quadrart.Services.StorageService.StorageService;

import jakarta.validation.Valid;

import com.quadrart.Models.Quadro.Quadro;
import com.quadrart.Models.Quadro.RequestQuadro;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * Abaixo está o controlador que se responsibiliza pelo login,
 * registro e logout.
 */

/*
 * @CrossOrigin(origins="{Seu_front_end}", allowCredentials="true");
 * 
 * Só utilize isso durante o desenvolvimento para integrar seu front
 * com o back durante o desenvolvimento, antes do deploy, remover 
 * o CrossOrigin.
 */
@RestController
@RequestMapping("/quadro")
public class QuadroController {

    /*
     * Abaixo seguem os objetos que serão utilizados na lógica dos controladores,
     * como
     * por exemplo:
     * - QuadroService (Objeto para manutenção de quadros na database);
     * - StorageService (Objeto para manutenção de upload de imagens);
     */

    @Autowired
    private QuadroService quadroService;

    @Autowired
    private StorageService storageService;

    /*
     * Esse endpoint abaixo é responsável por carregar todos os quadros da base de dados. 
     * o quadroService é utilizado para pegar uma lista de Quadros e retorna ao requisitor.
     */
    @GetMapping
    public ResponseEntity<List<Quadro>> getAllQuadros() {
        List<Quadro> quadros = quadroService.getAllQuadros();
        return ResponseEntity.ok(quadros);
    }

    /*
     * Esse endpoint abaixo é responsável por carregar apenas um quadro na base de dados.
     * O quadroService é utilizado para pegar um quadro com id especifico.
     * 
     * Como a função pode acabar não retornando nada, é necessário utilizar
     * o objeto Optional para evitar error throwing.
     * 
     * Depois, é verificado se esse objeto Optional<Quadro> tem algo dentro
     * de si ou não. E após isso, uma resoposta 200 ou 404 é retornada.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getQuadro(@PathVariable Long id) {
        Optional<Quadro> optionalQuadro = quadroService.getQuadro(id);

        if (optionalQuadro.isPresent()) {
            Quadro quadro = optionalQuadro.get();

            return ResponseEntity.ok().body(quadro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Esse é o caminho para criação de quadros. Esse caminho recebe as informações
     * via Multipart/form-data, pois, é muito mais fácil receber arquivos por esse
     * formato.
     * 
     * O @Valid verifica os campos do record RequestQuadro, utilizando as tags
     * definidas dentro de RequestQuadro.
     * 
     * O arquivo então é extraido para um parametro file, que será utilizado para
     * verificar os tipos permitidos de imagens a receberem upload. No caso da imagem
     * não ser compativel, um erro é retornado.
     * 
     * Após todas as checagens, um objeto Quadro é criado, e o nome da imagem é definido
     * através da criação de um identificador único que será obtido se obtendo os milissegundos
     * em tempo unix. Esse identificador é somado com o nome original do arquivo e então ele
     * é convertido para Sha256. Esse valor passará agora a ser o nome do arquivo quando salvado
     * no sistema de arquivos. o objeto quadro só terá salvo dentro de si, o nome da imagem
     * antes de ser no MySQL.
     * 
     * Se a criação ocorrer normalmente, uma resposta 200 é gerada.
     * 
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createQuadro(@Valid @ModelAttribute RequestQuadro requestQuadro) {
        MultipartFile file = requestQuadro.file();

        String fileType;
        if (file.getContentType().equals("image/jpg")) {
            fileType = ".jpg";
        } else if (file.getContentType().equals("image/jpeg")) {
            fileType = ".jpeg";
        } else if (file.getContentType().equals("image/png")) {
            fileType = ".png";
        } else {
            return ResponseEntity.badRequest().body("File not supported");
        }
        Quadro quadro = new Quadro(requestQuadro);

        String uniqueIdentifier = String.valueOf(System.currentTimeMillis());

        String dataWithUniqueId = file.getOriginalFilename() + uniqueIdentifier;

        String imagemfinal = DigestUtils.sha256Hex(dataWithUniqueId) + fileType;

        quadro.setImagem(imagemfinal);

        storageService.store(file, imagemfinal);

        quadroService.createQuadro(quadro);

        return ResponseEntity.ok(quadro);
    }


    /* 
     * Endpoint necessário para atualização de informações do quadro.
     * 
     * Inicialmente ele recebe um id, e as informações do quadro atualizado.
     * 
     * A função atualizarQuadro é chamada, e recebe um id e quadro para a atualização do quadro.
     * 
     * Retorna uma resposta 200 se estiver tudo ok.
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateQuadro(@PathVariable Long id, @Valid @RequestBody RequestQuadro requestQuadro) {
        Quadro quadro = quadroService.atualizarQuadro(id, new Quadro(requestQuadro));
        return ResponseEntity.ok(quadro);

    }

    /*
     * Endpoint necessário para se deletar um quadro.
     * 
     * Inicialmente ele recebe um id, que será utilizado na função deleteQuadro;
     */
    @DeleteMapping(path = "/{id}")
    public String deleteQuadro(@PathVariable Long id) {
        return quadroService.deleteQuadro(id);
    }

    /*
     * Endpoint necessário para acessar uma imagem atrelada um quadro de id especifico.
     * 
     * Inicialmente, ele recebe um id de um quadro que está atrelado a imagem.
     * 
     * Após isso, é buscado o quadro na base de dados. Como a função pode acabar não retornando
     * nada, é necessário utilizar o objeto Optional<Quadro> para receber o retorno da função.
     * 
     * Caso não seja encontrado o quadro, é retornado o status 404.
     * 
     * Caso seja encontrado, o nome da foto atrelada ao quadro é obtida,
     * e uma imagem é retornada para o requisitor juntamente com a resposta
     * 200.
     * 
     */
    @GetMapping(path = "/image/{id}")
    public ResponseEntity<?> getQuadroImage(@PathVariable Long id) {
        Optional<Quadro> optionalQuadro = quadroService.getQuadro(id);

        if (optionalQuadro.isPresent()) {
            Quadro quadro = optionalQuadro.get();
            Resource file = storageService.loadAsResource(quadro.getImagem());
            if (file == null) {
                return ResponseEntity.notFound().build();
            }
            String filename = file.getFilename();
            String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
            String contentType = "image/" + fileExtension.toLowerCase();

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(file);
        } else {
            // Handle the case where Quadro is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
