package com.jiespace.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by huangmingjie on 2017/10/20.
 */
@Data
public class DepartDetail {
    
    @NotBlank(message = "depart_city_code is Empty")
    private String depart_city_code;
    private String arrive_city_code;
    private String depart_date;
}
