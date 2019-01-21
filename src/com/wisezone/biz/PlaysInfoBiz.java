package com.wisezone.biz;

import com.wisezone.bean.PlaysInfo;

import java.util.List;
import java.util.Map;

public interface PlaysInfoBiz extends CommonBiz<PlaysInfo> {
    /**
     * 查询所有正在上映电影信息
     * @return
     */
    List<Map> queryAll();
}
