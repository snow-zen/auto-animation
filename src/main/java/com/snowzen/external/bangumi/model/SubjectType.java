package com.snowzen.external.bangumi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 条目类型。
 *
 * @author snow-zen
 */
@Getter
@RequiredArgsConstructor
public enum SubjectType {
    /**
     * 书籍。
     */
    BOOK(1),

    /**
     * 动画。
     */
    ANIMATION(2),

    /**
     * 音乐。
     */
    MUSIC(3),

    /**
     * 游戏。
     */
    GAME(4),

    /**
     * 三次元。
     */
    THREE_D(6);

    private final Integer value;
}
