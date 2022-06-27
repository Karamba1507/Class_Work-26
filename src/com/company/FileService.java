package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    private static final Gson gson=new GsonBuilder().setPrettyPrinting().create();
    private static final Path path=Paths.get("./datab.json");

    public static KeyValue[] readString() {
        String json;
        try {
            json=Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(json, KeyValue[].class);


    }

    public static void main(String[] args) {
        KeyValue[] dataBases=FileService.readString();
        for (KeyValue d : dataBases) {
            System.out.println(d);
        }
    }
}