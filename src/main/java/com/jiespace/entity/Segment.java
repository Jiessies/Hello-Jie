package com.jiespace.entity;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by huangmingjie on 2017/10/20.
 */
@Data
public class Segment {
    @Valid
    private List<DepartDetail> segment_list;
}
