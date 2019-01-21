package com.wisezone.biz;

import com.wisezone.bean.Film;

import java.util.List;

public interface FilmBiz extends CommonBiz<Film> {
    /**
     * 查询所有电影信息
     * @return
     */
    List<Film> queryAll();
}
