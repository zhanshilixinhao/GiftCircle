package com.yichen.auth.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yichen.auth.properties.ImageProperties;

import java.io.IOException;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/23
 */

public class ImgUrlJsonSerializer extends JsonSerializer<Object> {

    private ImageProperties imageProperties;

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        String host = imageProperties == null ? "" : imageProperties.getHost();
        if (value instanceof String) {
            if (((String) value).startsWith("http")) {
                gen.writeString((String) value);
            } else
            gen.writeString(String.format("%s%s", host, value));
            return;
        }
        if (value instanceof List) {
            List list = (List) value;
            int i = 0;
            for (Object str : list) {
                if (!(str instanceof String)) {
                    break;
                }
                if (((String) str).startsWith("http")) continue;
                list.set(i++, String.format("%s%s", host, str));
            }
        }
        gen.writeObject(value);
    }

    public void setImageProperties(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }
}
