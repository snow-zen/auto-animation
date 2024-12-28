package com.snowzen.common.spec;

import lombok.Getter;

/**
 * @author snow-zen
 */
@Getter
public final class PageSpec {

    private int page = 0;

    private int size = 10;

    public PageSpec(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
