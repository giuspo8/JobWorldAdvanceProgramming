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
	 * Metodo che ritorna un oggetto di tipo null se il parametro passatogli è nullo,
	 * altrimenti lo converte nel tipo @Timestamp e lo ritorna
	 * 
	 * @param oggetto di tipo @Long da convertire
	 */
	@Override
	public Timestamp convertToDatabaseColumn(Long time) {
		Timestamp date = new Timestamp(time);
		return date;
	}

	/**
	 * Metodo che ritorna un oggetto di tipo null se il parametro passatogli è nullo,
	 * altrimenti lo converte nel tipo @Long e lo ritorna
	 * 
	 * @param oggetto di tipo @Timestamp da convertire
	 */
	@Override
	public Long convertToEntityAttribute(Timestamp dbData) {
		return dbData == null ? null : dbData.getTime();
	}

}
