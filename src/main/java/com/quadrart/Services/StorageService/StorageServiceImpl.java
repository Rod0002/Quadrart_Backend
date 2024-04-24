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

/**
 * Storage
 */
import com.quadrart.Storage.StorageProperties;
import com.quadrart.Storage.StorageException;
import com.quadrart.Storage.StorageFileNotFoundException;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path imagesFolder;

    public StorageServiceImpl(StorageProperties properties) {
        if (properties.getLocation().trim().length() == 0) {
            throw new StorageException("O caminho para o upload do arquivo não pode ser vazio");
        }

        this.imagesFolder = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(imagesFolder);
        } catch (IOException e) {
            throw new StorageException("Não foi possível criar o diretório", e);
        }
    }

    @Override
    public void store(MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Não é um possivel salvar um arquivo sem dados");
            }

            Path destinationFile = this.imagesFolder.resolve(
                    Paths.get(filename)
                            .normalize()
                            .toAbsolutePath());

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

    @Override
    public Path load(String filename) {
        return imagesFolder.resolve(filename);
    }

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
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(imagesFolder.toFile());
    }

}
