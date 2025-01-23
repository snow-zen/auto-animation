package com.snowzen.external.bangumi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Bangumi 条目。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class Subject {

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
     * 摘要。
     */
    @JsonProperty("summary")
    private String summary;

    /**
     * 放送日期。当条目还未开始放送时，该字段值为 null。
     */
    @JsonProperty("date")
    private LocalDate airDate;

    /**
     * 平台。
     */
    @JsonProperty("platform")
    private String platform;

    /**
     * 封面图片。
     */
    @JsonProperty("images")
    private ImageData images;

    /**
     * 总集数。
     */
    @JsonProperty("total_episodes")
    private Integer totalEpisodes;
}
