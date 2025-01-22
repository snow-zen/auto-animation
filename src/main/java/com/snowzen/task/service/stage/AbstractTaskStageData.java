package com.snowzen.task.service.stage;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.snowzen.task.enums.TaskStages;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务阶段数据基类。不同类型的任务阶段存在不一样的结构，由子类进行扩展。
 *
 * @author snow-zen
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = FetchInfoTaskStageData.class, name = "FETCH_INFO")
})
@Getter
@Setter
public abstract class AbstractTaskStageData {

    /**
     * 任务阶段类型。
     */
    @NotNull
    private TaskStages type;
}
