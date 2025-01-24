package com.snowzen.common.enums;

import com.fasterxml.jackson.core.type.TypeReference;
import com.snowzen.common.ImageAssert;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 元数据信息附加信息字段枚举。
 *
 * @author snow-zen
 */
@Getter
@RequiredArgsConstructor
public enum MetaInfoAdditionalField {

    /**
     * 封面图片资源。
     */
    ANIMATION_COVER("animation.cover", new TypeReference<List<ImageAssert>>() {
    }),

    /**
     * 总集数。
     */
    ANIMATION_TOTAL_EPISODES("animation.total-episodes", new TypeReference<Integer>() {
    }),

    /**
     * 类型。
     */
    EPISODE_TYPE("episode.type", new TypeReference<ResourceType>() {
    }),

    /**
     * 主体内容排序。只有当 {@link #EPISODE_TYPE} 的值
     * 为 {@link ResourceType#MAIN_CONTENT} 时，排序才有意义。
     */
    EPISODE_MAIN_CONTENT_SORT("episode.main-content.sort", new TypeReference<Integer>() {
    });

    private final String key;
    private final TypeReference<?> typeReference;

}
