package com.snowzen.external.bangumi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * Bangumi 关联条目。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class SubjectRelation {

    /**
     * 条目 id。
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * 名称。
     */
    @JsonProperty("name")
    private String name;

    /**
     * 中文名称。
     */
    @JsonProperty("name_cn")
    private String nameCn;

    /**
     * 条目类型。
     */
    @JsonProperty("type")
    @JsonDeserialize(using = SubjectTypeDeserializer.class)
    private SubjectType type;

    /**
     * 封面图片。
     */
    @JsonProperty("images")
    private ImageData images;

    /**
     * 关联描述。
     */
    @JsonProperty("relation")
    private String relation;
}
