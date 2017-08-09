package br.com.sp.intranet.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date>{

	@Override
	public Date convertToDatabaseColumn(LocalDateTime localDateTime) {

		if (localDateTime != null) {
			Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
			return date;
		}
		return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Date date) {
		if (date != null) {
			LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
			return localDateTime;
		}
		return null;
	}
}
