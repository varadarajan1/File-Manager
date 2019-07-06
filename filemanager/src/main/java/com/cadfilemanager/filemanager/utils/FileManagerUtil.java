package com.cadfilemanager.filemanager.utils;

public final class FileManagerUtil {

    public static String convertSizeFromBytesToKiloBytes(long bytes){
       float sizeInKb = bytes/1000;
       return String.format("%.2f kb",sizeInKb);
    }
}
