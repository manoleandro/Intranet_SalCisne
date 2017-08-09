package wsr.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DataTypes implements Serializable {
	
	public static String encode(String text){
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(text);*/
		return null;
	}
	
	//Ordenar Map pelo value
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();

        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;

    }
    
    //Ordenar Map pela key
    public static <K extends Comparable<? super K>, V> Map<K, V> sortMapByKey(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;
    }
    
    public static boolean isNull(Long number){
		if(number == null || number == 0)
			return true;
		else
			return false;
	}
	
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			return isNull(obj.toString());
		}
	}

	public static boolean isNull(String s) {
		boolean retVal = true;
		if (s != null && !s.equals("") && s.trim().length() > 0 && !s.equalsIgnoreCase("null"))
			retVal = false;
		return retVal;
	}

	public static String parseNull(String s) {
		return isNull(s) ? "" : s;
	}

	public static Double parseOne(Double l) {
		return l == null || l == 0 ? new Double("1") : l;
	}

	public static String returnPercent(Double qtd, Double qtdTotal) {
		Double retorno = new Double(0);

		if (qtd != 0 && qtdTotal != 0)
			retorno = (qtd.doubleValue() / qtdTotal.doubleValue()) * 100;

		return Math.round(retorno) + "%";
	}

	public static Object parseNull(Object obj) {
		if (obj == null) {
			return "";

		} else if (obj instanceof String) {
			return parseNull(obj.toString());

		} else if (obj instanceof Date) {
			return parseNull((Date) obj);

		} else if (obj instanceof BigDecimal) {
			return parseNull((BigDecimal) obj);

		} else if (obj instanceof Long) {
			return parseNull((Long) obj);

		} else if (obj instanceof Double) {
			return parseNull((Double) obj);

		} else {
			return obj.toString();
		}
	}
	
	public static String parseNullObject(Object obj){
		return obj != null ? obj.toString() : null;
		
	}

	public static BigDecimal parseNull(BigDecimal bd) {
		return bd != null ? bd : new BigDecimal(0);
	}

	public static Long parseNull(Long lg) {
		return lg != null ? lg : new Long(0);
	}

	public static Double parseNull(Double db) {
		return db != null ? db : new Double(0);
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
	
	public static Double parseZero(Double d){
		return d == null || d < 0 ? 0: d;
	}
	
	public static Long parseZero(Long l){
		return l == null || l< 0 ? 0: l;
	}
	
	public static final boolean isNumero(Object numero) {
		if (!DataTypes.isNull(numero)) {

			try {
				Long.parseLong(numero.toString());
				return true;

			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static final String quebraClausulaIN(String where, String codigos) {
		String retorno = "";
		String cod[] = codigos.split(",");
		retorno = where + " IN (";
		int cont = 0;

		for (int x = 0; x < cod.length; x++) {
			if (cont <= 100) {
				if (x + 1 != cod.length) {
					retorno = retorno + cod[x] + ",";
				} else {
					retorno = retorno + cod[x];
				}
			} else {
				retorno = retorno + cod[x] + ") OR " + where + " IN(";
				cont = 0;
			}
			cont++;
		}

		retorno = retorno + ")";

		return retorno;
	}

	public static String retornaStringDeList(List<String> listString) {
		String retorno = "";
		int cont = 0;
		for (String string : listString) {
			if (cont == 0) {
				retorno = string;
			} else {
				retorno = retorno + "," + string;
			}
			cont++;
		}
		return retorno;
	}
}
