package com.snowzen.task.controller.payload;

import com.snowzen.common.enums.MetaPlatform;
import com.snowzen.common.enums.ResourceLinkType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 抓取信息 Payload。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class FetchInfoPayload {

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

    @Getter
    @Setter
    private static class Meta {

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

    @Getter
    @Setter
    private static class ResourceLink {
        /**
         * 链接类型。
         */
        private ResourceLinkType linkType;
        /**
         * 链接。
         */
        private String link;
    }
}
