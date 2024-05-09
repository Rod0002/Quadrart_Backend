package com.quadrart.Services.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
=======
/**
 * Storage
 */
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
import com.quadrart.Storage.StorageProperties;
import com.quadrart.Storage.StorageException;
import com.quadrart.Storage.StorageFileNotFoundException;

<<<<<<< HEAD
/*
 * Serviço de storage, que é responsável por
 * ter funções que vão interagir fom o file system
 * podendo-se assim, salvar imagens, deleta-las e etc.
 */
@Service
public class StorageServiceImpl implements StorageService {

    /*
     * Variável Path, que contém um caminho.
     */
    private final Path imagesFolder;

    /*
     * Contrutor do serviço, que irá definir recebendo-se
     * o objeto StorageProperties, que contém o caminho,
     * onde as imagens serão salvars.
     * 
     * Inicialmente, verifica-se se o path do properties
     * não é vazinho, caso não seja, imagesFolder é definido.
     */
=======
@Service
public class StorageServiceImpl implements StorageService {

    private final Path imagesFolder;

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    public StorageServiceImpl(StorageProperties properties) {
        if (properties.getLocation().trim().length() == 0) {
            throw new StorageException("O caminho para o upload do arquivo não pode ser vazio");
        }

<<<<<<< HEAD
        this.imagesFolder = Paths.get(properties.getLocation()).normalize().toAbsolutePath();
    }

    /*
     * Função que cria um diretório onde as imagens serão salvas. Primeiro
     * se tenta criar um diretório, e em caso de falha, um erro é jogado.
     */
=======
        this.imagesFolder = Paths.get(properties.getLocation());
    }

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public void init() {
        try {
            Files.createDirectories(imagesFolder);
        } catch (IOException e) {
            throw new StorageException("Não foi possível criar o diretório", e);
        }
    }

<<<<<<< HEAD
    /*
     * Essa função salva um arquivo na pasta criada.
     * 
     * Ela recebe um arquivo e uma string chamada
     * nome do arquivo. Caso o arquivo esteja vazio
     * um erro é jogado.
     * 
     * Caso não, haja erro, o caminho de onde o arquivo
     * é construido é criado.
     * 
     * Caso o pai do caminho gerado. (Exemplo, se um caminho é x/a, logo o pai é x)
     * não seja ao caminho absoluto, que é o caminho onde as imagens são salvas, um
     * erro é jogado.
     * 
     * Caso seja, se tenta criar o arquivo na pasta criada, e caso haja erro no
     * processo
     * de criação um erro é jogado
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public void store(MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Não é um possivel salvar um arquivo sem dados");
            }

            Path destinationFile = this.imagesFolder.resolve(
<<<<<<< HEAD
                    Paths.get(filename))
                    .normalize()
                    .toAbsolutePath();
=======
                    Paths.get(filename)
                            .normalize()
                            .toAbsolutePath());
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78

            if (!destinationFile.getParent().equals(this.imagesFolder.toAbsolutePath())) {
                throw new StorageException("Não se pode salvar um arquivo fora do diretório atual");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Não foi possível salvar o arquivo");
        }
    }

<<<<<<< HEAD
    /*
     * Carrega todas as imagens da basse de dados. Através da função walk, todos os
     * arquivos
     * são lidos e retornados.
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.imagesFolder, 1)
                    .filter(path -> !path.equals(this.imagesFolder))
                    .map(this.imagesFolder::relativize);
        } catch (IOException e) {
            throw new StorageException("Houve um erro ao ler os arquivos armazenados");
        }
    }

<<<<<<< HEAD
    /*
     * Retorna o caminho de onde uma imagem de dado nome se encontra.
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public Path load(String filename) {
        return imagesFolder.resolve(filename);
    }

<<<<<<< HEAD
    /*
     * Retorna uma imagem no formato Resource. Primeiro
     * se obtem o nome do arquivo. Com o nome do arquivo
     * o caminho de onde ele se encontra é obtido.
     * Após isso, a imagem é transoformada em um objeto
     * Resource, caso a imagem tenha sido encontrada
     * e seja possível ler ela, o objeto resource é retornado,
     * 
     * caso não, um erro é jogado.
     */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Não foi possível ler o arquivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Não foi possível ler o arquivo: " + filename, e);
        }
    }

    @Override
<<<<<<< HEAD
    public void delete(String filename) {
        Path destinationFile = this.imagesFolder.resolve(
                Paths.get(filename))
                .normalize()
                .toAbsolutePath();

        if (!destinationFile.getParent().equals(this.imagesFolder.toAbsolutePath())) {
            throw new StorageException("Não se pode deletar um arquivo fora do diretório atual");
        }

        try {
            Files.deleteIfExists(destinationFile);
        } catch (IOException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }

    }

    /*
     * Responsável por assassinar todas as imagens uma dada pasta.
     */
    @Override
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(imagesFolder.toFile());
    }

}
