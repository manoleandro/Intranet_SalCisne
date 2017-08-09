package com.mdSync.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

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
}