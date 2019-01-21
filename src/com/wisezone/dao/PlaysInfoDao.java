package com.wisezone.dao;

import com.wisezone.bean.PlaysInfo;

import java.util.List;
import java.util.Map;

public interface PlaysInfoDao extends CommonDao<PlaysInfo> {
    /**
     * 查询所有正在上映电影信息
     * @return
     */
    List<Map> queryAll();
}
