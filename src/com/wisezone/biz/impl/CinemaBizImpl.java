package com.wisezone.biz.impl;

import com.wisezone.bean.Cinema;
import com.wisezone.biz.CinemaBiz;
import com.wisezone.dao.CinemaDao;
import com.wisezone.dao.impl.CinemaDaoImpl;

import java.util.List;

public class CinemaBizImpl implements CinemaBiz {
    private CinemaDao dao = new CinemaDaoImpl();
    @Override
    public List<Cinema> queryByFilmId(int filmId) {
        return dao.queryByFilmId(filmId);
    }

    @Override
    public int add(Cinema cinema) {
        return dao.add(cinema);
    }

    @Override
    public int update(Cinema cinema) {
        return dao.update(cinema);
    }

    @Override
    public int remove(int id) {
        return dao.remove(id);
    }

    @Override
    public Cinema queryOne(int id) {
        return dao.queryOne(id);
    }
}
