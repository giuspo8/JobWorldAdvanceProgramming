package jobworld.utils;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Classe utility che ci serve per convertire il tipo LocalDate in Date e
 * viceversa in quanto nel db non � utilizzabile.
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	/**
	 * ritorna un oggetto di tipo null se il parametro passatogli � nullo,
	 * altrimenti lo converte nel tipo @Date e lo ritorna
	 * 
	 * @param oggetto di tipo @LocalDate da convertire
	 */
	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		return attribute == null ? null : Date.valueOf(attribute);
	}

	/**
	 * ritorna un oggetto di tipo null se il parametro passatogli � nullo,
	 * altrimenti lo converte nel tipo @LocalDate e lo ritorna
	 * 
	 * @param oggetto di tipo @Date da convertire
	 */
	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		return dbData == null ? null : dbData.toLocalDate();
	}

}