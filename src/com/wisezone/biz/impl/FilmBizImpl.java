package com.wisezone.biz.impl;

import com.wisezone.bean.Film;
import com.wisezone.biz.FilmBiz;
import com.wisezone.dao.FilmDao;
import com.wisezone.dao.impl.FilmDaoImpl;

import java.util.List;

public class FilmBizImpl implements FilmBiz {

    private FilmDao dao = new FilmDaoImpl();

    @Override
    public List<Film> queryAll() {
        return dao.queryAll();
    }

    @Override
    public int add(Film film) {
        return dao.add(film);
    }

    @Override
    public int update(Film film) {
        return dao.update(film);
    }

    @Override
    public int remove(int id) {
        return dao.remove(id);
    }

    @Override
    public Film queryOne(int id) {
        return dao.queryOne(id);
    }
}
