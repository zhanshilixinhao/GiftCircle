package com.yichen.auth.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.yichen.auth.properties.ImageProperties;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author yichenshanren
 * @date 2017/12/18
 */
public class MyBeanSerializerModifier extends BeanSerializerModifier {

    private JsonSerializer<Object> _nullArrayJsonSerializer = new MyNullArrayJsonSerializer();

    private JsonSerializer<Object> _nullStringJsonSerializer = new MyNullStringJsonSerializer();

    private ImgUrlJsonSerializer imgUrlJsonSerializer = new ImgUrlJsonSerializer();

    private ImageProperties imageProperties;

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            checkImageUrl(writer);
            if (isString(writer)) {
//                writer.assignSerializer(this._nullStringJsonSerializer);
                String name = writer.getName();
                if (!StringUtils.equalsAny(name, "msg", "path")) {
                    writer.assignNullSerializer(this._nullStringJsonSerializer);
                }
            } else
                // 判断字段的类型，如果是array，list，set则注册nullSerializer
                if (isArrayType(writer)) {
                    //给writer注册一个自己的nullSerializer
                    writer.assignNullSerializer(this.defaultNullArrayJsonSerializer());
                }
        }
        return beanProperties;
    }

    /**
     * 判断是否需要拼接图片地址
     *
     * @param writer
     * @author yichenshanren
     * @date 2017/12/18
     */
    private void checkImageUrl(BeanPropertyWriter writer) {
        if (imageProperties == null || !imageProperties.isNeedSplice()) {
            return;
        }
        ImgUrl im = writer.getAnnotation(ImgUrl.class);
        if (im != null) {
            writer.assignSerializer(imgUrlJsonSerializer);
        }
    }

    private boolean isString(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getPropertyType();
        return clazz.equals(String.class);
    }

    // 判断是不是集合类型
    protected boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getPropertyType();
        return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);

    }

    protected JsonSerializer<Object> defaultNullArrayJsonSerializer() {
        return _nullArrayJsonSerializer;
    }

    public void setImageProperties(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
        imgUrlJsonSerializer.setImageProperties(imageProperties);
    }
}