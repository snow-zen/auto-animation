package com.snowzen.meta;

import com.snowzen.common.ApiResponseStatus;
import com.snowzen.common.enums.MetaPlatform;
import com.snowzen.common.exception.ApiException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.Optional;

/**
 * 管理上下文中所有类型为 {@link MetaInfoFetcher} 的 Bean，提供一个统一的方法
 * 给调用方。方法内部选择合适的 Bean 进行调度执行。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class MetaInfoFetcherManager {

    @Any
    @Inject
    Instance<MetaInfoFetcher> fetchers;

    /**
     * 获取元数据信息。
     *
     * @param metaPlatform 元数据平台枚举。
     * @param target 元数据标识。
     * @return 元数据树，一个元数据可能关联其他附加元数据，使用 optional 进行包装。
     */
    public Optional<MetaInfoTreeNode> fetch(MetaPlatform metaPlatform, String target) {
        try {
            for (MetaInfoFetcher fetcher : fetchers) {
                if (fetcher.support(metaPlatform)) {
                    return fetcher.fetch(target);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new ApiException("元数据抓取执行失败", ApiResponseStatus.TASK_STAGE_EXECUTE_FAIL);
        }
    }
}
