package com.snowzen.common;

import lombok.Getter;

/**
 * 图片资源。
 *
 * @param width  宽度。
 * @param height 高度。
 * @param src    url 路径。
 * @author snow-zen
 */
@Getter
public record ImageAssert(Integer width, Integer height, String src) {

}
