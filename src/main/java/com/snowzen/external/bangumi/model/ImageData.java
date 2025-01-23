package com.snowzen.external.bangumi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 目录的图像资源。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class ImageData {

    private String large;

    private String common;

    private String medium;

    private String small;

    private String grid;

}
