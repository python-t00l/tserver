package com.titan.tserver.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties("storage")
public class StorageProperties {


    public String getRootlocation() {
        return rootlocation;
    }

    public void setRootlocation(String rootlocation) {
        this.rootlocation = rootlocation;
    }

    /**
     * Folder location for storing files
     *
     */

    private String rootlocation = "UploadFiles";

    private String videolocation = "UploadFiles"+ File.separator+"video";

    private String Imagelocation = "UploadFiles"+File.separator+"imgs";

    public String getVideolocation() {
        return videolocation;
    }

    public void setVideolocation(String videolocation) {
        this.videolocation = videolocation;
    }

    public String getImagelocation() {
        return Imagelocation;
    }

    public void setImagelocation(String imagelocation) {
        Imagelocation = imagelocation;
    }




}
