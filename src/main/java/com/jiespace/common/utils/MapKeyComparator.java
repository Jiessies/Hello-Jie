package com.jiespace.common.utils;

import java.util.Comparator;

/**
 * Created by huangmingjie on 2017/10/20.
 */
public class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
