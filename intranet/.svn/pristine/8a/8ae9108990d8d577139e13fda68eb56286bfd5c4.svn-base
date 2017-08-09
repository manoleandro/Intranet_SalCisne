package br.com.sp.intranet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class PropertyUtils {
    private static Properties propriedades;

    public static Properties getInstance(String nomeArquivo) {
        if (propriedades == null) {
            propriedades = new Properties();
            PropertyUtils.initPropriedades(nomeArquivo);
        }
        return propriedades;
    }

    public static Properties initPropriedades(String nomeArquivo) {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		String caminhoArquivo = scontext.getRealPath("/properties/" + nomeArquivo + ".properties");
		
        File file = new File(caminhoArquivo);
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