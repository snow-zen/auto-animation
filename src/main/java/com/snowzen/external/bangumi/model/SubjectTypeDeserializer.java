package com.snowzen.external.bangumi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/**
 * 枚举类型 {@link SubjectType} 对应的反序列化器。
 *
 * @author snow-zen
 * @see SubjectType
 */
class SubjectTypeDeserializer extends JsonDeserializer<SubjectType> {
    @Override
    public SubjectType deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext) throws IOException {
        Throwable cause = null;
        try {
            Integer value = Integer.valueOf(jsonParser.getText());
            for (SubjectType subjectType : SubjectType.values()) {
                if (subjectType.getValue().equals(value)) {
                    return subjectType;
                }
            }
        } catch (Exception e) {
            cause = e;
        }
        throw new IllegalArgumentException("Unknown subject type: " + jsonParser.getText(), cause);
    }
}
