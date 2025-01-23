package com.snowzen.external.bangumi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Bangumi 章节。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class Episode {

    /**
     * 章节 id。
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * 类型。
     */
    @JsonProperty("type")
    @JsonDeserialize(using = EpisodeTypeDeserializer.class)
    private EpisodeType type;

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
     * 同类条目的排序和集数。
     */
    @JsonProperty("sort")
    private Double sort;

    /**
     * 条目内的集数, 从 1 开始。非本篇剧集的此字段无意义。
     */
    @JsonProperty("ep")
    private Double ep;

    /**
     * 放送日期。
     */
    @JsonProperty("airdate")
    private LocalDate airDate;

    /**
     * 简介。
     */
    @JsonProperty("desc")
    private String description;
}
