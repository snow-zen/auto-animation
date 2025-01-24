package com.snowzen.common.util;

import com.snowzen.common.ImageAssert;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 图片工具。
 *
 * @author snow-zen
 */
@Slf4j
public final class ImageUtil {

    private ImageUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 通过图片 url 获取图片相关信息。
     *
     * @param imageUrl 图片 url。
     * @return 包装图片信息的 optional 对象。
     */
    public static Optional<ImageAssert> fetchImageInfo(String imageUrl) {
        if (StringUtils.isBlank(imageUrl)) {
            return Optional.empty();
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(URI.create(imageUrl).toURL());
            return Optional.of(new ImageAssert(bufferedImage.getWidth(), bufferedImage.getHeight(), imageUrl));
        } catch (IOException e) {
            log.error("获取图片信息失败", e);
            return Optional.empty();
        }
    }
}
