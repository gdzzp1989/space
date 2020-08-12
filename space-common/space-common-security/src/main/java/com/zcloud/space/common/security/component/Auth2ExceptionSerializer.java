package com.zcloud.space.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zcloud.space.common.core.constant.Constants;
import com.zcloud.space.common.security.exception.MyAuth2Exception;
import lombok.SneakyThrows;

/**
 * @Description OAuth2 异常格式化
 * @Author
 * @Date
 */
public class Auth2ExceptionSerializer extends StdSerializer<MyAuth2Exception> {

    public Auth2ExceptionSerializer() {
        super(MyAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(MyAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", Constants.FAIL);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }
}
