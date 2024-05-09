package com.quadrart.Services.StorageService;

import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

<<<<<<< HEAD
/*
 * Interface de serviço Storage. Onde é definido
 * as funções que serão implementadas de forma obrigatória.
 */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
public interface StorageService {
    void init();

    void store(MultipartFile file, String filename);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

<<<<<<< HEAD
    void delete(String filename);

=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
    void deleteAll();
}
