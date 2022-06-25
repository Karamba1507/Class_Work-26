package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    public static DataBase[] readFile() {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            final Path path = Paths.get("./datab.json");

        String json;
        try {
            json=Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return gson.fromJson(json, DataBase[].class);
    }

    

        
    

//    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    private static final Path path = Paths.get("./datab.json");
//
//    public static DataBase[] readString(){
//        String json;
//        try {
//            json = Files.readString(path);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
      //System.out.println("|-----+------------------+--------|");
//        System.out.println("|   # |  Driver          |  Bus   |");
//        System.out.println("|-----+------------------+--------|");
//        System.out.println("|------> Enter track's id: <------|");
//
//
//        return gson.fromJson(json, DataBase[].class);
//
//
//    }
//
//
//    public static void writeFile(DataBase[] dataBases){
//
//        String json = gson.toJson(dataBases);
//
//        byte[] arr = json.getBytes();
//        try {
//            Files.write(path, arr);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) {
        DataBase[] dataBases = FileService.readFile();
        System.out.println(dataBases);
        for (DataBase d:
             dataBases) {
            System.out.println(d);
        }
    }
}