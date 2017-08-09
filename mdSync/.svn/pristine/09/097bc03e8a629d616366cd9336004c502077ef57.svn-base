package com.mdSync.util;

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
    public static final long umDiaMilli = 86400000;
    private static final HashMap<String, SimpleDateFormat> simpleDateFormats;
    private Calendar calendar;
    private Locale locale = new Locale("pt", "BR");

    static {
        simpleDateFormats = new HashMap();
    }

    public static DateUtils getInstance() {
        if (dateUtils == null) {
            dateUtils = new DateUtils();
        }
        return dateUtils;
    }

    public SimpleDateFormat getSimpleDateFormat(String key) {
        if (simpleDateFormats.containsKey(key)) {
            return simpleDateFormats.get(key);
        }
        SimpleDateFormat df = new SimpleDateFormat(key, new Locale("pt", "BR"));
        GregorianCalendar calendar = new GregorianCalendar();
        df.setCalendar(calendar);
        simpleDateFormats.put(key, df);
        return df;
    }

    public String obtemData(Date data) {
        return this.obtemData(data, "dd/MM/yy");
    }

    public String obtemDataHora(Date data) {
        return this.obtemData(data, "dd/MM/yy hh:mm:ss");
    }

    public String obtemData(Date data, String fmt) {
        String resultado = "";
        if (data != null) {
            SimpleDateFormat df = this.getSimpleDateFormat(fmt);
            resultado = df.format(data);
        }
        return resultado;
    }

    public static Date retornaMesAno(String entrada) {
        SimpleDateFormat sf = new SimpleDateFormat("MM/yyyy");
        Date retorno = new Date();
        try {
            retorno = sf.parse(entrada);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public static String trataDataMySql(Date entrada) {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = "";
        dataStr = entrada != null ? sf.format(entrada) : "";
        return dataStr;
    }

    public static Long retornaMilisegundoDeHora(String strHora) {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        Date hora = null;
        try {
            hora = sf.parse(strHora);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return hora.getTime();
    }

    public long retornaDiferencaEmDias(Date date1, Date data2) {
        return (date1.getTime() - data2.getTime()) / 86400000;
    }

    public int retornaDiferencaEmDias(String data1, String data2) {
        return (int)((this.obtemData(data2).getTime() - this.obtemData(data1).getTime()) / 86400000);
    }

    public Date retornaDataUltimoDiaMes(String data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.obtemData("01/" + data));
        String ultimoDia = "" + calendar.getActualMaximum(5);
        return this.obtemData(String.valueOf(ultimoDia) + "/" + data);
    }

    public static String retornaHoraDeMili(Long lMilis) {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        sf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String hora = null;
        try {
            hora = sf.format(lMilis);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return hora;
    }

    public String retornaHoraSomada(Double hora) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "BR"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Integer qtdDias = (int)(hora / 8.64E7);
        String horas = dateFormat.format(hora);
        if (qtdDias > 0) {
            qtdDias = qtdDias * 24;
            String[] s1 = null;
            for (int i = 0; i <= horas.length(); ++i) {
                s1 = horas.split(":");
            }
            Integer horasDia = new Integer(s1[0]);
            Integer resultado = new Integer("0");
            resultado = horasDia + qtdDias;
            horas = String.valueOf(resultado.toString()) + ":" + s1[1] + ":" + s1[2];
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
        return this.obtemData(data, "dd/MM/yyyy");
    }

    public Date obtemDatayyyyMMdd(String data) {
        return this.obtemData(data, "yyyyMMdd");
    }

    public Date obtemDataHora(String data) {
        return this.obtemData(data, "dd/MM/yy HH:mm:ss");
    }

    public Date obtemDataHoraSemSec(String data) {
        return this.obtemData(data, "dd/MM/yy hh:mm");
    }

    public Date obtemHora(String data) {
        return this.obtemData(data, "HH:mm");
    }

    public String obtemHora(Date data) {
        return this.obtemData(data, "HH:mm");
    }

    public Date obtemData(String data, String fmt) {
        Date resultado = null;
        if (data != null) {
            try {
                SimpleDateFormat df = this.getSimpleDateFormat(fmt);
                resultado = df.parse(data);
            }
            catch (ParseException e) {
                return null;
            }
        }
        return resultado;
    }

    public int retornaDiaDoMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.get(5);
    }

    public int retornaMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.get(2);
    }

    public int retornaAno(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.get(1);
    }

    public int retornaDiaDaSemana(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.get(7);
    }

    public Date retornaDiaSemanaAnterior(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        data = calendar.get(7) == 2 ? this.adicionaDias(data, -3) : this.adicionaDias(data, -1);
        return data;
    }

    public Date adicionaDias(Date data, Integer dias) {
        try {
            SimpleDateFormat df = dateUtils.getSimpleDateFormat("dd/MM/yyyy");
            String dataAntStr = df.format(data);
            String[] valores = dataAntStr.split("/");
            Integer dia = new Integer(Integer.parseInt(valores[0]) + dias);
            return df.parse(String.valueOf(dia.toString()) + "/" + valores[1] + "/" + valores[2]);
        }
        catch (ParseException e) {
            return null;
        }
    }

    public Date addMonth(int month, Date date) {
        this.calendar = Calendar.getInstance(this.locale);
        this.calendar.setTime(date);
        this.calendar.add(2, month);
        return this.calendar.getTime();
    }

    public Date adicionaMeses(Date data, Integer meses) {
        try {
            SimpleDateFormat df = dateUtils.getSimpleDateFormat("dd/MM/yyyy");
            String dataAntStr = df.format(data);
            String[] valores = dataAntStr.split("/");
            Integer mes = new Integer(Integer.parseInt(valores[1]) + meses);
            return df.parse(String.valueOf(valores[0]) + "/" + mes.toString() + "/" + valores[2]);
        }
        catch (ParseException e) {
            return null;
        }
    }

    public int retornaUltimoDiaMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.getActualMaximum(5);
    }

    public int retornaNumeroDias(Date menorData, Date maiorData) {
        Calendar d1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        d1.setTime(menorData);
        long m1 = d1.getTimeInMillis();
        Calendar d2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        d2.setTime(maiorData);
        long m2 = d2.getTimeInMillis();
        return (int)((m2 - m1) / 86400000);
    }

    public long retornaMilisegundosDaHora(String hora) {
        long result = 0;
        if (!hora.equals("0")) {
            String[] aux = hora.split(":");
            long hoursInMillis = new Long(aux[0]) * 60 * 60 * 1000;
            long minutesInMillis = new Long(aux[1]) * 60 * 1000;
            long secondsInMillis = 0;
            if (aux.length > 2) {
                secondsInMillis = new Long(aux[2]) * 1000;
            }
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
        ArrayList<Calendar> datas = new ArrayList<Calendar>();
        long[] v = new long[intervalo];
        GregorianCalendar calendar = new GregorianCalendar();
        Date dataEntrada = DateUtils.trataDataMesString(entrada);
        calendar.setTime(dataEntrada);
        datas.add(calendar);
        GregorianCalendar calendarTemp = new GregorianCalendar();
        calendarTemp.setTime(dataEntrada);
        for (int i = 0; i < intervalo; ++i) {
            GregorianCalendar calendarLoop = new GregorianCalendar();
            calendarLoop.setTime(calendarTemp.getTime());
            v[i] = DateUtils.mesAntMilli(calendarTemp.get(2) + 1, calendarTemp.get(1), calendarLoop.getTimeInMillis());
            calendarLoop.setTimeInMillis(v[i]);
            calendarTemp.setTimeInMillis(v[i]);
            datas.add(calendarLoop);
        }
        return datas;
    }

    public static String getNomeMes(int mes) {
        if (mes == 1) {
            return "Janeiro";
        }
        if (mes == 2) {
            return "Fevereiro";
        }
        if (mes == 3) {
            return "Mar\u00ef\u00bf\u00bdo";
        }
        if (mes == 4) {
            return "Abril";
        }
        if (mes == 5) {
            return "Maio";
        }
        if (mes == 6) {
            return "Junho";
        }
        if (mes == 7) {
            return "Julho";
        }
        if (mes == 8) {
            return "Agosto";
        }
        if (mes == 9) {
            return "Setembro";
        }
        if (mes == 10) {
            return "Outubro";
        }
        if (mes == 11) {
            return "Novembro";
        }
        if (mes == 12) {
            return "Dezembro";
        }
        return "";
    }

    public static boolean isBisexto(int ano) {
        int v = 0;
        if (ano % 400 == 0) {
            v = ano / 100;
            if (v % 4 == 0) {
                return true;
            }
            return false;
        }
        if (ano % 4 == 0) {
            return true;
        }
        return false;
    }

    public static long mesAntMilli(int mes, int ano, long atual) {
        if (mes == 1) {
            return atual - 2678400000L;
        }
        if (mes == 2) {
            return atual - 2678400000L;
        }
        if (mes == 3) {
            if (DateUtils.isBisexto(ano)) {
                return atual - 2505600000L - 3600000;
            }
            return atual - 2419200000L - 3600000;
        }
        if (mes == 4) {
            return atual - 2678400000L;
        }
        if (mes == 5) {
            return atual - 2592000000L;
        }
        if (mes == 6) {
            return atual - 2678400000L;
        }
        if (mes == 7) {
            return atual - 2592000000L;
        }
        if (mes == 8) {
            return atual - 2678400000L;
        }
        if (mes == 9) {
            return atual - 2678400000L;
        }
        if (mes == 10) {
            return atual - 2592000000L;
        }
        if (mes == 11) {
            if (DateUtils.isBisexto(ano)) {
                return atual - 2764800000L + 90000000;
            }
            return atual - 2592000000L - 79200000;
        }
        if (mes == 12) {
            return atual - 2592000000L;
        }
        return 0;
    }

    public static int verificaDiasMes(int mes) {
        if (mes == 1) {
            return 31;
        }
        if (mes == 2) {
            return 28;
        }
        if (mes == 3) {
            return 31;
        }
        if (mes == 4) {
            return 30;
        }
        if (mes == 5) {
            return 31;
        }
        if (mes == 6) {
            return 30;
        }
        if (mes == 7) {
            return 31;
        }
        if (mes == 8) {
            return 31;
        }
        if (mes == 9) {
            return 30;
        }
        if (mes == 10) {
            return 31;
        }
        if (mes == 11) {
            return 30;
        }
        if (mes == 12) {
            return 31;
        }
        return 0;
    }

    public static Date criarData(String s, String pattern) {
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        Date retorno = null;
        if (!(s == null || s.isEmpty())) {
            try {
                retorno = sd.parse(s);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }

    public static Date trataDataBarraString(String entrada) {
        Date data = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = format.parse(entrada);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Date trataDataString(String entrada) {
        Date data = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            data = format.parse(entrada);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String trataDataDiaMes(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM");
        String dataStr = "";
        dataStr = entrada != null ? format.format(entrada) : "";
        return dataStr;
    }

    public static String trataData(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = "";
        dataStr = entrada != null ? format.format(entrada) : "";
        return dataStr;
    }

    public static String trataDia(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        String dataStr = format.format(entrada);
        return dataStr;
    }

    public static String trataDataInterface(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dataStr = "";
        dataStr = entrada != null ? format.format(entrada) : "";
        return dataStr;
    }

    public static String trataMesAno(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("MMyyyy");
        String dataStr = format.format(entrada);
        return dataStr;
    }

    public static String trataAnoMes(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String dataStr = format.format(entrada);
        return dataStr;
    }

    public static String retornaDiaPorData(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String dataStr = format.format(entrada);
        return dataStr;
    }

    public static String retornaMesPorData(Date entrada) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String dataStr = format.format(entrada);
        return dataStr;
    }

    public static Date trataDiaString(String entrada) {
        Date data = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
            data = format.parse(entrada);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Date trataDataMesString(String entrada) {
        Date data = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MMyyyy");
            data = format.parse(entrada);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean verificaIntervaloAno(Date data) {
        Date dataAtual = new Date();
        Long dataNow = dataAtual.getTime();
        Long dataParam = data.getTime();
        Long dataDiferenca = dataNow - dataParam;
        Long totalAno = 31536000000L;
        if (dataDiferenca > totalAno) {
            return false;
        }
        return true;
    }

    public static int verificaMesAno(int diaInicial, int diaFinal, int mesInicial, int mesFinal, int intervaloAno) {
        int qtdDias = 0;
        if (intervaloAno == 0) {
            if (mesFinal > mesInicial) {
                for (int i = mesInicial; i <= mesFinal; ++i) {
                    if (i == mesInicial) {
                        qtdDias+=DateUtils.verificaDiasMes(i) - diaInicial;
                        continue;
                    }
                    if (i == mesFinal) {
                        int mesCorr = DateUtils.verificaDiasMes(i) - diaFinal;
                        qtdDias+=DateUtils.verificaDiasMes(i) - mesCorr;
                        continue;
                    }
                    qtdDias+=DateUtils.verificaDiasMes(i);
                }
            } else if (mesFinal == mesInicial) {
                qtdDias = diaInicial >= diaFinal ? diaInicial - diaFinal : diaFinal - diaInicial;
            }
            return qtdDias;
        }
        for (int i = 0; i <= intervaloAno; ++i) {
            int j;
            if (i == 0) {
                for (j = mesInicial; j <= 12; ++j) {
                    qtdDias+=DateUtils.verificaDiasMes(j);
                }
                continue;
            }
            if (i == intervaloAno) {
                for (j = 1; j <= mesFinal; ++j) {
                    qtdDias+=DateUtils.verificaDiasMes(j);
                }
                continue;
            }
            qtdDias+=365;
        }
        qtdDias-=diaInicial;
        qtdDias-=DateUtils.verificaDiasMes(mesFinal);
        return qtdDias+=diaFinal;
    }

    public static String somaDuasDatas(int dia, int mes, int ano, int diaFinal, int mesFinal, int anoFinal, boolean contaDias) {
        int somaDias = 0;
        int contador = 0;
        int somaMes = mes;
        int somaAno = ano;
        int verifica = 3;
        int qtdAnos = anoFinal - ano;
        contador = somaDias = DateUtils.verificaMesAno(dia, diaFinal, mes, mesFinal, qtdAnos);
        while (verifica != 0) {
            if (somaMes == 1) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 2) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 3) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 4) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 5) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 6) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 7) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 8) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 9) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 10) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 11) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes != 12) continue;
            if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                somaDias-=DateUtils.verificaDiasMes(somaMes);
                somaMes = 1;
                ++somaAno;
                continue;
            }
            verifica = 0;
            break;
        }
        if (contaDias) {
            return String.valueOf(contador);
        }
        if (somaMes < 10) {
            if (somaDias < 10) {
                return "0" + String.valueOf(somaDias) + "0" + String.valueOf(somaMes) + String.valueOf(somaAno);
            }
            return String.valueOf(String.valueOf(somaDias)) + "0" + String.valueOf(somaMes) + String.valueOf(somaAno);
        }
        if (somaDias < 10) {
            return "0" + String.valueOf(somaDias) + String.valueOf(somaMes) + String.valueOf(somaAno);
        }
        return String.valueOf(String.valueOf(somaDias)) + String.valueOf(somaMes) + String.valueOf(somaAno);
    }

    public static String somaDias(int dia, int mes, int ano, int valor, boolean contaDias) {
        int somaDias = dia + valor;
        int somaMes = mes;
        int somaAno = ano;
        int contador = 0;
        int verifica = 3;
        if (somaDias < DateUtils.verificaDiasMes(mes)) {
            contador = somaDias;
        }
        while (verifica != 0) {
            if (somaMes == 1) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 2) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 3) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 4) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 5) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 6) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 7) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 8) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 9) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 10) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes == 11) {
                if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                    somaDias-=DateUtils.verificaDiasMes(somaMes);
                    contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                    ++somaMes;
                } else {
                    verifica = 0;
                    break;
                }
            }
            if (somaMes != 12) continue;
            if (somaDias > DateUtils.verificaDiasMes(somaMes)) {
                somaDias-=DateUtils.verificaDiasMes(somaMes);
                contador = DateUtils.verificaDiasMes(somaMes) - dia + valor;
                somaMes = 1;
                ++somaAno;
                continue;
            }
            verifica = 0;
            break;
        }
        if (contaDias) {
            return String.valueOf(contador);
        }
        if (somaMes < 10) {
            if (somaDias < 10) {
                return "0" + String.valueOf(somaDias) + "0" + String.valueOf(somaMes) + String.valueOf(somaAno);
            }
            return String.valueOf(String.valueOf(somaDias)) + "0" + String.valueOf(somaMes) + String.valueOf(somaAno);
        }
        if (somaDias < 10) {
            return "0" + String.valueOf(somaDias) + String.valueOf(somaMes) + String.valueOf(somaAno);
        }
        return String.valueOf(String.valueOf(somaDias)) + String.valueOf(somaMes) + String.valueOf(somaAno);
    }
}

