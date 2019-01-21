package com.wisezone.bean;

import java.util.Date;
import java.util.Objects;

/**
 * 电影类
 */
public class Film {
    private int cId;
    private String cName;
    private String cType;
    private int times;
    private String location;
    private Date inDate;

    public Film() {
    }

    public Film(int cId, String cName, String cType, int times, String location, Date inDate) {
        this.cId = cId;
        this.cName = cName;
        this.cType = cType;
        this.times = times;
        this.location = location;
        this.inDate = inDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return cId == film.cId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId);
    }

    @Override
    public String toString() {
        return "Film{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cType='" + cType + '\'' +
                ", times=" + times +
                ", location='" + location + '\'' +
                ", inDate=" + inDate +
                '}';
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }
}
