package com.example.bean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @author hzq
 * @date 2019/7/28 0028 下午 4:51
 */
public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String userName = node.get("user-name").asText();
        User user = new User();
        user.setUserName(userName);
        return user;
    }
}
