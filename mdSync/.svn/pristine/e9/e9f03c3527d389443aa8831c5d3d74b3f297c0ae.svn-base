package com.mdSync.util;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DataTypes
implements Serializable {
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return DataTypes.isNull(obj.toString());
    }

    public boolean criarXMLFormatado(String xml, String local) {
        try {
            File file = new File(local);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
            file.createNewFile();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = db.parse(is);
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            StringWriter out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
            FileWriter fw = new FileWriter(file);
            fw.write(out.toString());
            fw.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isNull(String s) {
        boolean retVal = true;
        if (!(s == null || s.equals("") || s.trim().length() <= 0 || s.equalsIgnoreCase("null"))) {
            retVal = false;
        }
        return retVal;
    }

    public static String parseNull(String s) {
        return DataTypes.isNull(s) ? "" : s;
    }

    public static Double parseOne(Double l) {
        return l == null || l == 0.0 ? new Double("1") : l;
    }

    public static Object parseNull(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return DataTypes.parseNull(obj.toString());
        }
        if (obj instanceof Date) {
            return DataTypes.parseNull((Date)obj);
        }
        if (obj instanceof BigDecimal) {
            return DataTypes.parseNull((BigDecimal)obj);
        }
        if (obj instanceof Long) {
            return DataTypes.parseNull((Long)obj);
        }
        if (obj instanceof Double) {
            return DataTypes.parseNull((Double)obj);
        }
        return obj.toString();
    }

    public static BigDecimal parseNull(BigDecimal bd) {
        return bd != null ? bd : new BigDecimal(0);
    }

    public static Long parseNull(Long lg) {
        return lg != null ? lg : new Long(0);
    }

    public static Double parseNull(Double db) {
        return db != null ? db : new Double(0.0);
    }

    public static Integer parseNull(Integer i) {
        return i != null ? i : new Integer(0);
    }

    public static String parseZero(String l) {
        return l == null || l.isEmpty() ? "0" : l;
    }

    public static String parseNegative(String l) {
        return l == null || l.isEmpty() ? "-1" : l;
    }

    public static final boolean isNumero(Object numero) {
        if (!DataTypes.isNull(numero)) {
            try {
                Long.parseLong(numero.toString());
                return true;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}

