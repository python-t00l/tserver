package com.titan.tserver.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    //void stoesVideo(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String contentType, String filename);

    Resource loadAsResource(String contentType,String filename);

    void deleteAll();

}
