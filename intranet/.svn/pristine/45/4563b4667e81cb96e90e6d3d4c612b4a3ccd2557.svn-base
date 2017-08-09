package br.com.sp.intranet.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public final class DateUtils {

	private static DateUtils dateUtils;
	public static final long umDiaMilli = 86400000L;
	private static final HashMap<String, SimpleDateFormat> simpleDateFormats = new HashMap<String, SimpleDateFormat>();

	private Calendar calendar;
	private Locale locale = new Locale("pt", "BR");

	public static DateUtils getInstance() {

		if (dateUtils == null) {
			dateUtils = new DateUtils();
		}
		return dateUtils;
	}

	public static SimpleDateFormat getSimpleDateFormat(String key) {

		if (simpleDateFormats.containsKey(key)) {
			return (SimpleDateFormat) simpleDateFormats.get(key);
		} else {
			SimpleDateFormat df = new SimpleDateFormat(key, ConstantUtils.BRASIL);
			Calendar calendar = new GregorianCalendar();
			df.setCalendar(calendar);
			simpleDateFormats.put(key, df);
			return df;
		}
	}

	public String obtemData(Date data) {

		return obtemData(data, "dd/MM/yy");
	}

	public String obtemDataHora(Date data) {

		return obtemData(data, "dd/MM/yy hh:mm:ss");
	}

	public static String obtemData(Date data, String fmt) {

		String resultado = "";
		if (data != null) {
			SimpleDateFormat df = getSimpleDateFormat(fmt);
			resultado = df.format(data);
		}
		return resultado;
	}
	
	public static Date formatarData(Date data, String fmt) {
		if (data != null) {
			SimpleDateFormat df = getSimpleDateFormat(fmt);
			return obtemData(df.format(data), fmt);
		}
		return null;
	}

	// Rafael
	public Integer retornaMesAnterior(String mes) {
		Integer mesAnterior = new Integer(mes);

		if (mesAnterior == 1) {
			mesAnterior = 12;
		} else {
			mesAnterior = mesAnterior - 1;
		}

		return mesAnterior;
	}

	// Rafael
	public boolean compararDatasEmString(Date data1, Date data2, String fmt) {
		if (obtemData(data1, fmt).equals(obtemData(data2, fmt))) {
			return true;
		} else {
			return false;
		}
	}

	// Rafael
	public static Date retornaMesAno(String entrada) {
		SimpleDateFormat sf = new SimpleDateFormat("MM/yyyy");
		Date retorno = new Date();
		try {
			retorno = sf.parse(entrada);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	// Rafael
	public static String retornaMesAno(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("MM/yyyy");
		String data = sf.format(date);

		return data;
	}

	// Rafael
	public static String trataDataMySql(Date entrada) {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "";
		if (entrada != null) {
			dataStr = sf.format(entrada);
		} else {
			dataStr = "";
		}
		return dataStr;
	}

	// Rafael
	public static Long retornaMilisegundoDeHora(String strHora) {
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
		Date hora = null;
		try {
			hora = sf.parse(strHora);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return hora.getTime();
	}

	// Rafael
	public long retornaDiferencaEmDias(Date date1, Date data2) {
		return ((date1.getTime() - data2.getTime()) / 86400000);
	}

	// Rafael
	public int retornaDiferencaEmDias(String data1, String data2) {

		return (int) ((obtemData(data2).getTime() - obtemData(data1).getTime()) / 86400000);
	}

	// Rafael - recebe formato MM/yyyy
	public Date retornaDataUltimoDiaMes(String data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(obtemData("01/" + data));
		String ultimoDia = "" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		return obtemData(ultimoDia + "/" + data);
	}

	// Rafael
	public Date addMonth(final int month) {
		calendar = Calendar.getInstance(locale);
		calendar.add(Calendar.MONTH, month);

		return calendar.getTime();
	}

	// Rafael
	public Date addMonth(final int month, Date date) {
		calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);

		return calendar.getTime();
	}

	// Rafael
	public Date addDay(final int day, Date date) {
		calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);

		return calendar.getTime();
	}

	// Rafael trata ano bissexto na mão. jan = 0
	public long retornaUltimoDiaDoMes(Date data) {
		long dia = 0;
		switch (this.retornaMes(data)) {
		case 0:
		case 2:
		case 4:
		case 6:
		case 7:
		case 9:
		case 11:
			dia = 31;
			break;
		case 3:
		case 5:
		case 8:
		case 10:
			dia = 30;
			break;
		case 1:
			int ano = this.retornaAno(data);
			if ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0)
				dia = 29;
			else
				dia = 28;
			break;
		}

		return dia;
	}

	public static String retornaHoraDeMili(Long lMilis) {
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String hora = null;
		try {
			hora = sf.format(lMilis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hora;
	}

	public String retornaHoraSomada(Double hora) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "BR"));
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		Integer qtdDias = (int) (hora / 86400000);
		String horas = dateFormat.format(hora);

		if (qtdDias.intValue() > 0) {
			qtdDias = qtdDias.intValue() * 24;
			String s1[] = null;
			for (int i = 0; i <= horas.length(); i++) {
				s1 = horas.split(":");
			}
			Integer horasDia = new Integer(s1[0]);
			Integer resultado = new Integer("0");
			resultado = horasDia + qtdDias;
			horas = resultado.toString() + ":" + s1[1] + ":" + s1[2];
		}
		return horas;
	}

	public String obtemDataHora(Date data, String fmt) {
		String resultado = "";
		if (data != null) {
			SimpleDateFormat df = this.getSimpleDateFormat(fmt);
			resultado = df.format(data);
		}
		return resultado;
	}

	public Date obtemData(String data) {

		return obtemData(data, "dd/MM/yy");
	}

	public Date obtemDataHora(String data) {

		return obtemData(data, "dd/MM/yy HH:mm:ss");
	}

	public Date obtemDataHoraSemSec(String data) {

		return obtemData(data, "dd/MM/yy hh:mm");
	}

	public Date obtemHora(String data) {

		return obtemData(data, "HH:mm");
	}

	public String obtemHora(Date data) {

		return obtemData(data, "HH:mm");
	}

	public static Date obtemData(String data, String fmt) {

		Date resultado = null;
		if (data != null) {
			try {
				SimpleDateFormat df = getSimpleDateFormat(fmt);
				resultado = df.parse(data);
			} catch (ParseException e) {
				return null;
			}
		}
		return resultado;
	}

	public int retornaDiaDoMes(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public int retornaMes(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.MONTH);
	}

	public int retornaAno(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.YEAR);
	}

	public int retornaDiaDaSemana(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public Date retornaDiaSemanaAnterior(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
			data = adicionaDias(data, -3);
		} else {
			data = adicionaDias(data, -1);
		}

		return data;
	}

	public Date adicionaDias(Date data, Integer dias) {

		try {
			SimpleDateFormat df = dateUtils.getSimpleDateFormat("dd/MM/yyyy");
			String dataAntStr = df.format(data);
			String[] valores = dataAntStr.split("/");
			Integer dia = new Integer(Integer.parseInt(valores[0]) + dias.intValue());

			return df.parse(dia.toString() + "/" + valores[1] + "/" + valores[2]);
		} catch (ParseException e) {
			return null;
		}
	}

	public Date adicionaMeses(Date data, Integer meses) {

		try {
			SimpleDateFormat df = dateUtils.getSimpleDateFormat("dd/MM/yyyy");
			String dataAntStr = df.format(data);
			String[] valores = dataAntStr.split("/");
			Integer mes = new Integer(Integer.parseInt(valores[1]) + meses.intValue());

			return df.parse(valores[0] + "/" + mes.toString() + "/" + valores[2]);
		} catch (ParseException e) {
			return null;
		}
	}

	public int retornaUltimoDiaMes(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int retornaNumeroDias(Date menorData, Date maiorData) {

		Calendar d1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		d1.setTime(menorData);
		long m1 = d1.getTimeInMillis();
		Calendar d2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		d2.setTime(maiorData);
		long m2 = d2.getTimeInMillis();
		// long m1 = menorData.getTime();
		// long m2 = maiorData.getTime();
		return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));

	}

	/**
	 * Metodo para retornar a qtde de milisegundos a partir de uma periodicidade
	 * 
	 * @param hora:
	 *            string no formato hh:mm:ss ou hh:mm
	 * @return
	 */
	public long retornaMilisegundosDaHora(String hora) {

		long result = 0;
		if (!hora.equals("0")) {
			String[] aux = hora.split(":");
			long hoursInMillis = new Long(aux[0]).longValue() * 60 * 60 * 1000;
			long minutesInMillis = new Long(aux[1]).longValue() * 60 * 1000;
			long secondsInMillis = 0;
			if (aux.length > 2)
				secondsInMillis = new Long(aux[2]).longValue() * 1000;

			result = hoursInMillis + minutesInMillis + secondsInMillis;
		}

		return result;
	}

	public static String intervaloEntreDatas(Date dataInicial, Date dataFim, boolean contaDias) {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		String data = formatter.format(dataInicial);
		int strDia = Integer.parseInt(data.toString().substring(0, 2));
		int strMes = Integer.parseInt(data.toString().substring(2, 4));
		int strAno = Integer.parseInt(data.toString().substring(4, 8));
		String dataF = formatter.format(dataFim);
		int strDiaF = Integer.parseInt(dataF.toString().substring(0, 2));
		int strMesF = Integer.parseInt(dataF.toString().substring(2, 4));
		int strAnoF = Integer.parseInt(dataF.toString().substring(4, 8));
		return DateUtils.somaDuasDatas(strDia, strMes, strAno, strDiaF, strMesF, strAnoF, contaDias);
	}

	public static List<Calendar> trataEntrada(String entrada, int intervalo) {
		List<Calendar> datas = new ArrayList<Calendar>();
		long v[] = new long[intervalo];
		Calendar calendar = new GregorianCalendar();
		Date dataEntrada = DateUtils.trataDataMesString(entrada);
		// System.out.println(dataEntrada.toString());
		calendar.setTime(dataEntrada);
		datas.add(calendar);
		Calendar calendarTemp = new GregorianCalendar();
		calendarTemp.setTime(dataEntrada);
		for (int i = 0; i < intervalo; i++) {
			Calendar calendarLoop = new GregorianCalendar();
			calendarLoop.setTime(calendarTemp.getTime());
			v[i] = DateUtils.mesAntMilli(calendarTemp.get(Calendar.MONTH) + 1, calendarTemp.get(Calendar.YEAR),
					calendarLoop.getTimeInMillis());
			calendarLoop.setTimeInMillis(v[i]);
			calendarTemp.setTimeInMillis(v[i]);
			datas.add(calendarLoop);
			// System.out.println(calendarLoop.getTime().toString());
		}
		return datas;
	}

	public static String getNomeMes(int mes) {
		if (mes == 1) {
			return "Janeiro";
		} else if (mes == 2) {
			return "Fevereiro";
		} else if (mes == 3) {
			return "Março";
		} else if (mes == 4) {
			return "Abril";
		} else if (mes == 5) {
			return "Maio";
		} else if (mes == 6) {
			return "Junho";
		} else if (mes == 7) {
			return "Julho";
		} else if (mes == 8) {
			return "Agosto";
		} else if (mes == 9) {
			return "Setembro";
		} else if (mes == 10) {
			return "Outubro";
		} else if (mes == 11) {
			return "Novembro";
		} else if (mes == 12) {
			return "Dezembro";
		} else {
			return "";
		}
	}

	public static boolean isBisexto(int ano) {
		int v = 0;
		if (ano % 400 == 0) {
			v = ano / 100;
			if (v % 4 == 0) {
				return true;
			} else {
				return false;
			}
		}
		if (ano % 4 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static long mesAntMilli(int mes, int ano, long atual) {
		if (mes == 1) {
			return atual - (31 * umDiaMilli);
		} else if (mes == 2) {
			return atual - (31 * umDiaMilli);
		} else if (mes == 3) {
			if (isBisexto(ano)) {
				return atual - (29 * umDiaMilli) - (60 * 60000);
			} else {
				return atual - (28 * umDiaMilli) - (60 * 60000);
			}
		} else if (mes == 4) {
			return atual - (31 * umDiaMilli);
		} else if (mes == 5) {
			return atual - (30 * umDiaMilli);
		} else if (mes == 6) {
			return atual - (31 * umDiaMilli);
		} else if (mes == 7) {
			return atual - (30 * umDiaMilli);
		} else if (mes == 8) {
			return atual - (31 * umDiaMilli);
		} else if (mes == 9) {
			return atual - (31 * umDiaMilli);
		} else if (mes == 10) {
			return atual - (30 * umDiaMilli);
		} else if (mes == 11) {
			if (isBisexto(ano)) {
				return atual - (32 * umDiaMilli) + (25 * 60 * 60000);
			} else {
				return atual - (30 * umDiaMilli) - (22 * 60 * 60000);
			}
		} else if (mes == 12) {
			return atual - (30 * umDiaMilli);
		} else {
			return 0;
		}
	}

	public static int verificaDiasMes(int mes) {
		if (mes == 1) {
			return 31;
		} else if (mes == 2) {
			return 28;
		} else if (mes == 3) {
			return 31;
		} else if (mes == 4) {
			return 30;
		} else if (mes == 5) {
			return 31;
		} else if (mes == 6) {
			return 30;
		} else if (mes == 7) {
			return 31;
		} else if (mes == 8) {
			return 31;
		} else if (mes == 9) {
			return 30;
		} else if (mes == 10) {
			return 31;
		} else if (mes == 11) {
			return 30;
		} else if (mes == 12) {
			return 31;
		} else {
			return 0;
		}
	}

	public static Date criarData(String s, String pattern) {
		SimpleDateFormat sd = new SimpleDateFormat(pattern);
		Date retorno = null;
		if ((s != null) && (!s.isEmpty())) {
			try {
				retorno = sd.parse(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;

	}

	public static Date trataDataBarraString(String entrada) {
		Date data = null;
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			data = (Date) format.parse(entrada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static Date trataDataString(String entrada) {
		Date data = null;
		try {
			DateFormat format = new SimpleDateFormat("ddMMyyyy");
			data = (Date) format.parse(entrada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String trataDataDiaMes(Date entrada) {
		DateFormat format = new SimpleDateFormat("dd/MM");
		String dataStr = "";
		if (entrada != null) {
			dataStr = format.format(entrada);
		} else {
			dataStr = "";
		}
		return dataStr;
	}

	public static String trataData(Date entrada) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "";
		if (entrada != null) {
			dataStr = format.format(entrada);
		} else {
			dataStr = "";
		}
		return dataStr;
	}

	public static String trataDia(Date entrada) {
		DateFormat format = new SimpleDateFormat("ddMMyyyy");
		String dataStr = format.format(entrada);
		return dataStr;
	}

	public static String trataDataInterface(Date entrada) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dataStr = "";
		if (entrada != null) {
			dataStr = format.format(entrada);
		} else {
			dataStr = "";
		}
		return dataStr;
	}

	public static String trataMesAno(Date entrada) {
		DateFormat format = new SimpleDateFormat("MMyyyy");
		String dataStr = format.format(entrada);
		return dataStr;
	}

	public static String trataAnoMes(Date entrada) {
		DateFormat format = new SimpleDateFormat("yyyyMM");
		String dataStr = format.format(entrada);
		return dataStr;
	}

	public static String retornaDiaPorData(Date entrada) {
		DateFormat format = new SimpleDateFormat("dd");
		String dataStr = format.format(entrada);
		return dataStr;
	}

	public static String retornaMesPorData(Date entrada) {
		DateFormat format = new SimpleDateFormat("MM");
		String dataStr = format.format(entrada);
		return dataStr;
	}

	public static Date trataDiaString(String entrada) {
		Date data = null;
		try {
			DateFormat format = new SimpleDateFormat("ddMMyy");
			data = (Date) format.parse(entrada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static Date trataDataMesString(String entrada) {
		Date data = null;
		try {
			DateFormat format = new SimpleDateFormat("MMyyyy");
			data = (Date) format.parse(entrada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static boolean verificaIntervaloAno(Date data) {
		Date dataAtual = new Date();
		Long dataNow = dataAtual.getTime();
		Long dataParam = data.getTime();
		Long dataDiferenca = dataNow - dataParam;
		Long totalAno = 86400000L * 365;
		if (dataDiferenca > totalAno) {
			return false;
		} else {
			return true;
		}
	}

	public static int verificaMesAno(int diaInicial, int diaFinal, int mesInicial, int mesFinal, int intervaloAno) {
		int qtdDias = 0;
		if (intervaloAno == 0) {
			if (mesFinal > mesInicial) {
				for (int i = mesInicial; i <= mesFinal; i++) {
					if (i == mesInicial) {
						qtdDias += verificaDiasMes(i) - diaInicial;
					} else if (i == mesFinal) {
						int mesCorr = verificaDiasMes(i) - diaFinal;
						qtdDias += verificaDiasMes(i) - mesCorr;
					} else {
						qtdDias += verificaDiasMes(i);
					}
				}
			} else if (mesFinal == mesInicial) {
				if (diaInicial >= diaFinal) {
					qtdDias = diaInicial - diaFinal;
				} else {
					qtdDias = diaFinal - diaInicial;
				}
			}
			return qtdDias;
		}
		for (int i = 0; i <= intervaloAno; i++) {
			if (i == 0) {
				for (int j = mesInicial; j <= 12; j++) {
					qtdDias += verificaDiasMes(j);
				}
			} else if (i == intervaloAno) {
				for (int j = 1; j <= mesFinal; j++) {
					qtdDias += verificaDiasMes(j);
				}
			} else {
				qtdDias += 365;
			}
		}
		qtdDias -= diaInicial;
		qtdDias -= verificaDiasMes(mesFinal);
		qtdDias += diaFinal;
		return qtdDias;

	}

	public static String somaDuasDatas(int dia, int mes, int ano, int diaFinal, int mesFinal, int anoFinal,
			boolean contaDias) {
		int somaDias = 0;
		int contador = 0;
		int somaMes = mes;
		int somaAno = ano;
		int verifica = 3;
		int qtdAnos = anoFinal - ano;
		somaDias = verificaMesAno(dia, diaFinal, mes, mesFinal, qtdAnos);
		contador = somaDias;

		while (verifica != 0) {
			if (somaMes == 1) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 2) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 3) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 4) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 5) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 6) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 7) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 8) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 9) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 10) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 11) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 12) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					somaMes = 1;
					somaAno++;
				} else {
					verifica = 0;
					break;
				}
			}
		}

		if (contaDias) {
			return String.valueOf(contador);
		} else {
			if (somaMes < 10) {
				if (somaDias < 10) {
					return "0" + String.valueOf(somaDias) + "0" + String.valueOf(somaMes) + ""
							+ String.valueOf(somaAno);
				} else {
					return String.valueOf(somaDias) + "0" + String.valueOf(somaMes) + "" + String.valueOf(somaAno);
				}
			} else {
				if (somaDias < 10) {
					return "0" + String.valueOf(somaDias) + "" + String.valueOf(somaMes) + "" + String.valueOf(somaAno);
				} else {
					return String.valueOf(somaDias) + "" + String.valueOf(somaMes) + "" + String.valueOf(somaAno);
				}
			}
		}
	}
	
	//Rafael
	public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

	public static String somaDias(int dia, int mes, int ano, int valor, boolean contaDias) {

		int somaDias = dia + valor;
		int somaMes = mes;
		int somaAno = ano;
		int contador = 0;
		int verifica = 3;

		if (somaDias < verificaDiasMes(mes)) {
			contador = somaDias;
		}

		while (verifica != 0) {
			if (somaMes == 1) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 2) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 3) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 4) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 5) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 6) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 7) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 8) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 9) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 10) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 11) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes++;
				} else {
					verifica = 0;
					break;
				}
			}
			if (somaMes == 12) {
				if (somaDias > verificaDiasMes(somaMes)) {
					somaDias = somaDias - verificaDiasMes(somaMes);
					contador = verificaDiasMes(somaMes) - dia + valor;
					somaMes = 1;
					somaAno++;
				} else {
					verifica = 0;
					break;
				}
			}
		}

		if (contaDias) {
			return String.valueOf(contador);
		} else {
			if (somaMes < 10) {
				if (somaDias < 10) {
					return "0" + String.valueOf(somaDias) + "0" + String.valueOf(somaMes) + ""
							+ String.valueOf(somaAno);
				} else {
					return String.valueOf(somaDias) + "0" + String.valueOf(somaMes) + "" + String.valueOf(somaAno);
				}
			} else {
				if (somaDias < 10) {
					return "0" + String.valueOf(somaDias) + "" + String.valueOf(somaMes) + "" + String.valueOf(somaAno);
				} else {
					return String.valueOf(somaDias) + "" + String.valueOf(somaMes) + "" + String.valueOf(somaAno);
				}
			}
		}
	}
}
