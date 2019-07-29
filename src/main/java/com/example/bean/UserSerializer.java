package com.example.bean;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author hzq
 * @date 2019/7/28 0028 下午 4:49
 */
public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user-name", user.getUserName());
        jsonGenerator.writeEndObject();
    }
}
