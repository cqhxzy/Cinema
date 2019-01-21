package com.wisezone.dao.impl;

import com.wisezone.bean.PlaysInfo;
import com.wisezone.dao.DBUitls;
import com.wisezone.dao.PlaysInfoDao;

import java.util.List;
import java.util.Map;

public class PlaysInfoDaoImpl extends DBUitls implements PlaysInfoDao {
    @Override
    public int add(PlaysInfo playsInfo) {
        String sql = "insert into filmplayinfo (cinameId,filmId) values (?,?)";
        Object[] params = {playsInfo.getCinema().getCid(),playsInfo.getFilm().getcId()};
        return super.executeUpdate(sql,params);
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


    @Override
    public List<Map> queryAll() {
        StringBuilder sql = new StringBuilder()
                .append("select t3.cname,t3.indate,t2.cinemaName")
                .append(" from filmplayinfo t1")
                .append(" inner join cinema t2")
                .append(" on t1.cinameId = t2.cid")
                .append(" inner join films t3")
                .append(" on t1.filmId = t3.cid");
        //一行数据就是一个map
        List<Map> maps = super.queryMap(sql.toString());
        return maps;
    }
}
