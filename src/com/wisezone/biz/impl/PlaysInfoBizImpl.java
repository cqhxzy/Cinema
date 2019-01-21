package com.wisezone.biz.impl;

import com.wisezone.bean.PlaysInfo;
import com.wisezone.biz.PlaysInfoBiz;
import com.wisezone.dao.PlaysInfoDao;
import com.wisezone.dao.impl.PlaysInfoDaoImpl;

import java.util.List;
import java.util.Map;

public class PlaysInfoBizImpl implements PlaysInfoBiz {
    private PlaysInfoDao dao = new PlaysInfoDaoImpl();
    @Override
    public List<Map> queryAll() {
        return dao.queryAll();
    }

    @Override
    public int add(PlaysInfo playsInfo) {
        return 0;
    }

    @Override
    public int update(PlaysInfo playsInfo) {
        return 0;
    }

    @Override
    public int remove(int id) {
        return 0;
    }

    @Override
    public PlaysInfo queryOne(int id) {
        return null;
    }
}
