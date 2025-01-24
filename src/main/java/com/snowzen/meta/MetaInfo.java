package com.snowzen.meta;

import com.snowzen.common.enums.MetaInfoAdditionalField;
import com.snowzen.common.enums.MetaPlatform;
import java.time.LocalDate;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * 元数据信息。
 *
 * @author snow-zen
 */
@Getter
@Setter
public class MetaInfo {

    /**
     * 来源平台。
     */
    private MetaPlatform originPlatform;

    /**
     * 来源标识。
     */
    private String originId;

    /**
     * 名称。
     */
    private String name;

    /**
     * 中文名称。
     */
    private String nameCn;

    /**
     * 摘要信息。
     */
    private String summary;

    /**
     * 放送日期。当元数据指定资源还未开始放送时，该字符值为 null。
     */
    private LocalDate airDate;

    /**
     * 附加内容，不同资源的元数据附加内容各不相同，key 定义
     * 和 value 的具体类型参考 {@link MetaInfoAdditionalField}。
     *
     * @see MetaInfoAdditionalField
     */
    private Map<String, Object> additionalData;
}
