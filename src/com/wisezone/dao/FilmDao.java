package com.wisezone.dao;

import com.wisezone.bean.Film;

import java.util.List;

public interface FilmDao extends CommonDao<Film> {
    /**
     * 查询所有电影信息
     * @return
     */
    List<Film> queryAll();
}
