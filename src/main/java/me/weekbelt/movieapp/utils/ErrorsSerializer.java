package me.weekbelt.movieapp.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        value.getFieldErrors().forEach(fieldError -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("field", fieldError.getField());
                gen.writeStringField("objectName", fieldError.getObjectName());
                gen.writeStringField("code", fieldError.getCode());
                gen.writeStringField("defaultMessage", fieldError.getDefaultMessage());
                Object rejectedValue = fieldError.getRejectedValue();
                if (rejectedValue != null) {
                    gen.writeStringField("rejectValue", rejectedValue.toString());
                }
                gen.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        value.getGlobalErrors().forEach(globalError -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("objectName", globalError.getObjectName());
                gen.writeStringField("code", globalError.getCode());
                gen.writeStringField("defaultMessage", globalError.getDefaultMessage());
                gen.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        gen.writeEndArray();
    }
}
