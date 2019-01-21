package com.wisezone.dao.impl;

import com.wisezone.bean.Film;
import com.wisezone.dao.DBUitls;
import com.wisezone.dao.FilmDao;

import java.util.List;

public class FilmDaoImpl extends DBUitls implements FilmDao {
    @Override
    public List<Film> queryAll() {
        String sql = "select t.cid,t.cname,t.ctype,t.times,t.location,t.indate from films t";
        return super.queryList(Film.class,sql);
    }

    @Override
    public int add(Film film) {
        String sql = "insert into films (cname,ctype,times,location,indate) values (?,?,?,?,?)";
        Object[] params = {film.getcName(),film.getcType(),film.getTimes(),film.getLocation(),film.getInDate()};
        return super.executeUpdate(sql,params);
    }

    @Override
    public int update(Film film) {
        String sql = "update films set cname=?,ctype=?,times=?,location=?,indate=? where cid=?";
        Object[] params = {film.getcName(),film.getcType(),film.getTimes(),film.getLocation(),film.getInDate(),film.getcId()};
        return super.executeUpdate(sql,params);
    }

    @Override
    public int remove(int id) {
        String sql = "delete from films where cid=?";

        return super.executeUpdate(sql,id);
    }

    @Override
    public Film queryOne(int id) {
        String sql = "select t.cid,t.cname,t.ctype,t.times,t.location,t.indate from films t where t.cid = ?";
        return super.queryOne(Film.class,sql,id);
    }
}
