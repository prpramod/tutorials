package org.baeldung.json.schema;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class JSONSchemaTest {

    @Test
    public void givenInvalidInput_whenValidating_thenInvalid() {

        JSONObject jsonSchema = new JSONObject(new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/schema.json")));
        JSONObject jsonSubject = new JSONObject(new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/product_invalid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);

        try {

            schema.validate(jsonSubject);
        }

        catch (ValidationException e) {

            System.out.println(e.getMessage());
            e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
        }
    }

    @Test
    public void givenValidInput_whenValidating_thenValid() {

        JSONObject jsonSchema = new JSONObject(new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/schema.json")));
        JSONObject jsonSubject = new JSONObject(new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/product_valid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);

        try {

            schema.validate(jsonSubject);
        }

        catch (ValidationException e) {

            System.out.println(e.getMessage());
            e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
        }
    }
}
