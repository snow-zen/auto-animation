package com.snowzen.external.bangumi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 章节类型。
 *
 * @author snow-zen
 */
@Getter
@RequiredArgsConstructor
public enum EpisodeType {

    /**
     * 本篇。
     */
    MAIN(0),

    /**
     * 特典。
     */
    SP(1),

    /**
     * 片头曲。
     */
    OP(2),

    /**
     * 片尾曲。
     */
    ED(3);

    private final Integer value;
}
