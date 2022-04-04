package com.framework.core.reader;

import com.framework.utils.PathUtils;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataFileReader {

    private String fileName;

    public DataFileReader(String fileName){

        this.fileName = fileName + ".yml";
    }

    public Map<String, Map<String, String>> readDataFile() throws Exception {

        Map<String, Map<String, String>> scenarioData = new HashMap<>();

        try {
            InputStream inputStream;

            Map<String, String> testCaseData;

            File dataFile = searchFile();

            if(null == dataFile){

                System.out.println("Data File not found with name: " + this.fileName);

                return scenarioData;
            }
            else {

                inputStream = new FileInputStream(dataFile);
            }
            Yaml yaml = new Yaml();

            Map<String, Object> data = yaml.load(inputStream);

            for (Map.Entry<String, Object> entry : data.entrySet()) {

                testCaseData = new HashMap<>();

                if (entry.getValue() instanceof Map) {

                    for (Map.Entry<String, Object> entry1 : ((Map<String, Object>) entry.getValue()).entrySet()) {

                        testCaseData.put(entry1.getKey(), entry1.getValue().toString());
                    }
                }
                scenarioData.put(entry.getKey(), testCaseData);
            }
        }
        catch(Exception e){
            if(e instanceof NoSuchFileException){

                throw new Exception("Data File not found with name :: " + this.fileName + "\n " + e.getMessage());
            }
            e.printStackTrace();
            throw new Exception("Error while reading data file : \n" + Arrays.toString(e.getStackTrace()));
        }

        return scenarioData;
    }

    private File searchFile() throws IOException {
        File dataFile = null;

        Collection<File> files = FileUtils.listFiles(new File(PathUtils.dataFolder), null, true);

        for (File file : files) {
            if (file.getName().equals(this.fileName)) {
                dataFile = file;
                System.out.println(file.getAbsolutePath());
            }
        }
        return dataFile;
    }

//    private File searchFile() throws IOException {
//        final File[] dataFile = new File[1];
//        try (Stream<Path> walkStream = Files.walk(Paths.get(PathUtils.dataFolder))) {
//            walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
//                if (f.toString().endsWith(this.fileName)) {
//                    dataFile[0] = f.getFileName().toFile().getAbsoluteFile();
//                    System.out.println(f + "data file found!");
//                }
//            });
//        }
//        return dataFile[0].getAbsoluteFile();
//    }
}
