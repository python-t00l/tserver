package com.titan.tserver.storage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path videoLocation;
    private final Path imgLocation;
    private final Path rootLocation;

    private static Logger logger = LogManager.getLogger(FileSystemStorageService.class);



    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.videoLocation = Paths.get(properties.getVideolocation());
        this.imgLocation = Paths.get(properties.getImagelocation());
        this.rootLocation=Paths.get(properties.getRootlocation());

    }

    @Override
    public void store(MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            filename = URLDecoder.decode(StringUtils.cleanPath(filename),"utf-8");
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            switch (file.getContentType()) {
                case "image/*":
                    Files.copy(file.getInputStream(), this.imgLocation.resolve(filename),
                            StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("存储路径"+imgLocation);
                    System.out.println("文件已存储"+filename);
                    break;
                case "video/*":
                    Files.copy(file.getInputStream(), this.videoLocation.resolve(filename),
                            StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("存储路径"+videoLocation);
                    logger.info("文件已存储"+filename);
                    System.out.println("文件已存储"+filename);
                    break;
            }


        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }



    @Override
    public Path load(String contentType,String filename) {
        switch (contentType){
            case "image/*":
                break;
            case "":
                break;
        }
        if(contentType.equals("image/*")){
            return imgLocation.resolve(filename);
        }else {
            return videoLocation.resolve(filename);

        }

        //return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String contentType,String filename) {
        try {
            Path file = load(contentType,filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            //Files.createDirectories(rootLocation);
            Files.createDirectories(videoLocation);
            Files.createDirectories(imgLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
