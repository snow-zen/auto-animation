package com.snowzen.task.service.stage;

import com.snowzen.common.ApiResponseStatus;
import com.snowzen.common.exception.ApiException;
import com.snowzen.meta.MetaInfoFetcherManager;
import com.snowzen.meta.MetaInfoTreeNode;
import com.snowzen.task.TaskExecutionContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

/**
 * 抓取任务阶段数据处理器。
 *
 * @author snow-zen
 */
@ApplicationScoped
public class FetchInfoTaskStageHandler implements TaskStageHandler<FetchInfoTaskStageData> {

    @Inject
    MetaInfoFetcherManager metaInfoFetcherManager;

    @Override
    public FetchInfoTaskStageData convertStageData(AbstractTaskStageData rawStageData) {
        if (rawStageData instanceof FetchInfoTaskStageData data) {
            return data;
        }
        return null;
    }

    @Override
    public boolean doHandle(FetchInfoTaskStageData stageData, TaskExecutionContext executionContext) {
        // 抓取数据
        FetchInfoTaskStageData.Meta meta = stageData.getMeta();
        MetaInfoTreeNode metaInfoTreeNode = metaInfoFetcherManager.fetch(meta.getPlatform(), meta.getTarget())
            .orElseThrow(() -> new ApiException("无法找到元数据信息", ApiResponseStatus.DATA_NOT_FOUND));

        // todo 保存元数据树到单独的表。

        if (CollectionUtils.isNotEmpty(stageData.getCandidateResourceLinks())) {
            // todo 解析候选资源链接
        }
        return true;
    }
}
