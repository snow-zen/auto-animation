package com.snowzen.external.bangumi.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/**
 * 枚举类型 {@link EpisodeType} 对应的反序列化器。
 *
 * @author snow-zen
 * @see EpisodeType
 */
class EpisodeTypeDeserializer extends JsonDeserializer<EpisodeType> {
    @Override
    public EpisodeType deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext) throws IOException, JacksonException {

        Throwable cause = null;
        try {
            Integer value = Integer.valueOf(jsonParser.getText());
            for (EpisodeType episodeType : EpisodeType.values()) {
                if (episodeType.getValue().equals(value)) {
                    return episodeType;
                }
            }
        } catch (Exception e) {
            cause = e;
        }
        throw new IllegalArgumentException("Unknown episode type: " + jsonParser.getText(), cause);
    }
}
