package com.snowzen.meta;

import com.snowzen.common.enums.MetaPlatform;
import java.util.Optional;

/**
 * 元数据抓取平台接口。
 *
 * @author snow-zen
 */
public interface MetaInfoFetcher {

    /**
     * 抓取元数据。
     *
     * @param target 目标标识，可以是 id 或者其他标识。
     * @return 抓取到的元数据信息包装的 optional 对象。
     */
    Optional<MetaInfoTreeNode> fetch(String target);

    /**
     * 实现是否支持指定元数据平台。
     *
     * @param platform 元数据平台枚举。
     * @return 支持返回 true，否则返回 false。
     */
    boolean support(MetaPlatform platform);
}
