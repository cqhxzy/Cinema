package com.wisezone.dao.impl;

import com.wisezone.bean.Cinema;
import com.wisezone.dao.CinemaDao;
import com.wisezone.dao.DBUitls;

import java.util.List;

public class CinemaDaoImpl extends DBUitls implements CinemaDao {
    @Override
    public List<Cinema> queryByFilmId(int filmId) {
        StringBuilder sql = new StringBuilder("select c.cid,c.cinemaName,c.address,c.phone from cinema c")
                .append("inner join (")
                .append("select t.cinameId from filmplayinfo t where t.filmId = ?")
                .append(") tt ")
                .append("on tt.cinameId = c.cid");

        return super.queryList(Cinema.class,sql.toString(),filmId);
    }

    @Override
    public int add(Cinema cinema) {
        String sql = "insert into cinema (cinemaName,address,phone) values (?,?,?)";
        Object[] params = {cinema.getCinemaName(),cinema.getAddress(),cinema.getPhone()};
        return super.executeUpdate(sql,params);
    }

    @Override
    public int update(Cinema cinema) {
        String sql = "update cinema set cinemaName=?,address=?,phone=? where cid=?";
        Object[] params = {cinema.getCinemaName(),cinema.getAddress(),cinema.getPhone(),cinema.getCid()};
        return super.executeUpdate(sql,params);
    }

    @Override
    public int remove(int id) {
        String sql = "delete from cinema where cid=?";
        return super.executeUpdate(sql,id);
    }

    @Override
    public Cinema queryOne(int id) {
        String sql = "select c.cid,c.cinemaName,c.address,c.phone from cinema c where c.cid=?";
        return super.queryOne(Cinema.class,sql,id);
    }
}
