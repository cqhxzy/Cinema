package com.wisezone.biz;

import com.wisezone.bean.Cinema;

import java.util.List;

public interface CinemaBiz extends CommonBiz<Cinema> {
    /**
     * 根据电影编号查询正在上映该电影的电影院
     * @param filmId
     * @return
     */
    List<Cinema> queryByFilmId(int filmId);
}
