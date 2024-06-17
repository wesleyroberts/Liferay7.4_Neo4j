package br.com.neo4j.Converter;


import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Type;

public class CustomConverter {

    private CustomConverter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Object convert(Value value) {
        Type type = value.type();

        switch (type.name()) {
            case "PATH":
                return value.asList(CustomConverter::convert);
            case "NODE":
            case "RELATIONSHIP":
                return value.asMap();
        }

        return value.asObject();
    }

}