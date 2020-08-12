package com.zcloud.space.common.core.dto;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @Description
 * @Author
 * @Date
 */
@Data
public class ConfigDTO {
    private String code;

    private String name;

    private String defaultValue;

    private String actualValue;

    @JsonIgnore
    public String getValue() {
        if (StrUtil.isNotBlank(this.actualValue)) {
            return this.actualValue;
        }
        return this.defaultValue;
    }
}
