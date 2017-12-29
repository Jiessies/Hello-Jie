package com.jiespace.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by huangmingjie on 2017/10/20.
 */
@Data
public class IFFlight {
    @NotNull(message = "openId is null")
    private String api_key;
    @Valid
    @NotNull(message = "data is Null")
    private Segment data;
    private String timestamp;
    @NotEmpty(message = "sign is Empty")
    private String sign;
}
