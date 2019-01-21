package com.wisezone.bean;

import java.util.Objects;

/**
 * 电影院上映的电影信息
 */
public class PlaysInfo {
    /*电影编号*/
    private Film film;
    /*电影院编号*/
    private Cinema cinema;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaysInfo playsInfo = (PlaysInfo) o;
        return Objects.equals(film, playsInfo.film) &&
                Objects.equals(cinema, playsInfo.cinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, cinema);
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public PlaysInfo() {
    }

    public PlaysInfo(Film film, Cinema cinema) {
        this.film = film;
        this.cinema = cinema;
    }
}
