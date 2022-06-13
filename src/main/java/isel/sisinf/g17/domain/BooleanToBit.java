package isel.sisinf.g17.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.postgresql.util.PGobject;

import java.sql.SQLException;

@Converter
public class BooleanToBit implements AttributeConverter<Boolean, Object> {
    @Override
    public Object convertToDatabaseColumn(Boolean value) {
        try {
            PGobject po = new PGobject();
            po.setType("BIT");
            if (value != null && value) {
                po.setValue("1");
            } else {
                po.setValue("0");
            }
            return po;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean convertToEntityAttribute(Object value) {
        return value == Boolean.TRUE;
    }
}

