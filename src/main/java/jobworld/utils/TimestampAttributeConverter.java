package jobworld.utils;


import java.sql.Timestamp;

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
public class TimestampAttributeConverter implements AttributeConverter<Long, Timestamp> {

	/**
	 * ritorna un oggetto di tipo null se il parametro passatogli � nullo,
	 * altrimenti lo converte nel tipo @Date e lo ritorna
	 * 
	 * @param oggetto di tipo @LocalDate da convertire
	 */
	@Override
	public Timestamp convertToDatabaseColumn(Long time) {
		Timestamp date = new Timestamp(time);
		return date;
	}

	/**
	 * ritorna un oggetto di tipo null se il parametro passatogli � nullo,
	 * altrimenti lo converte nel tipo @LocalDate e lo ritorna
	 * 
	 * @param oggetto di tipo @Date da convertire
	 */
	@Override
	public Long convertToEntityAttribute(Timestamp dbData) {
		return dbData == null ? null : dbData.getTime();
	}

}
