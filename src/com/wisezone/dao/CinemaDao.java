package com.wisezone.dao;

import com.wisezone.bean.Cinema;

import java.util.List;

public interface CinemaDao extends CommonDao<Cinema> {
    /**
     * 根据电影编号查询正在上映该电影的电影院
     * @param filmId
     * @return
     */
    List<Cinema> queryByFilmId(int filmId);
}
