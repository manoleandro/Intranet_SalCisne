package com.mdSync.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propriedades {
    private static Properties propriedades;

    public static Properties getInstance(String nomeArquivo) {
        if (propriedades == null) {
            propriedades = new Properties();
            Propriedades.initPropriedades(nomeArquivo);
        }
        return propriedades;
    }

    public static Properties initPropriedades(String nomeArquivo) {
        File file = new File(String.valueOf(nomeArquivo) + ".properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            propriedades.load(fis);
            fis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return propriedades;
    }
}