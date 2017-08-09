package br.com.sp.intranet.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

public class LocalDateUtils {
	
	private static final String FORMAT_MM_YYYY = "MM/yyyy";
	
	public static LocalDate obterDataPrimeiroDia(String date){
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
		builder.parseDefaulting(ChronoField.DAY_OF_MONTH, 1);
		builder.append(DateTimeFormatter.ofPattern(FORMAT_MM_YYYY));
		DateTimeFormatter dtf = builder.toFormatter();
		
		return LocalDate.parse(date, dtf);
	}
	
	public static LocalDate obterData(String date, String format){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

		return LocalDate.parse(date, formatter);
	}
	
	public static String formatarData(LocalDate data, String format){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return data.format(formatter);
	}
	
	public static LocalDate convertToLocalDate(Date date){
		LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
		
		return localDate;
	}
	
	public static Date convertToDate(LocalDate localDate){
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return date;
	}
}