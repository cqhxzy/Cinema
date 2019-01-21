package com.wisezone.bean;

import java.util.Objects;

/**
 * 电影院类
 */
public class Cinema {
    private int cid;
    private String cinemaName;
    private String address;
    private String  phone;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return cid == cinema.cid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid);
    }

    public Cinema() {
    }

    public Cinema(int cid, String cinemaName, String address, String phone) {
        this.cid = cid;
        this.cinemaName = cinemaName;
        this.address = address;
        this.phone = phone;
    }
}
