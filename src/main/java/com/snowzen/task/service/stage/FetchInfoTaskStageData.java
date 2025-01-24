package com.snowzen.task.service.stage;

import com.snowzen.common.enums.MetaPlatform;
import com.snowzen.common.enums.ResourceLinkType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 抓取信息任务阶段数据。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class FetchInfoTaskStageData extends AbstractTaskStageData {

    /**
     * 元数据。
     */
    @NotNull
    @Valid
    private Meta meta;

    /**
     * 候选下载地址。
     */
    @Valid
    private List<ResourceLink> candidateResourceLinks;

    /**
     * 元数据。
     */
    @Getter
    @Setter
    public static class Meta {

        /**
         * 元数据平台。
         */
        @NotNull
        private MetaPlatform platform;

        /**
         * 目标标识。
         */
        @NotNull
        private String target;
    }

    /**
     * 资源链接。
     */
    @Getter
    @Setter
    public static class ResourceLink {
        /**
         * 链接类型。
         */
        @NotNull
        private ResourceLinkType linkType;
        /**
         * 链接。
         */
        @NotNull
        private String link;
    }
}
