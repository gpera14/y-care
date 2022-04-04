package com.framework.core.managers;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlFileManager {

    private String fileName;

    public YamlFileManager(String fileName){

        this.fileName = fileName + ".yml";
    }

    public Map<String, String> read() throws FileNotFoundException {

        InputStream inputStream = new FileInputStream(new File(fileName));

        Yaml yaml = new Yaml();

        Map<String, String> data = yaml.load(inputStream);

        return data;
    }

}
