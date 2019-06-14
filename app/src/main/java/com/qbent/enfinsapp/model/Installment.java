package com.qbent.enfinsapp.model;

public class Installment
{
    private String id;
    private String periods;

    public Installment(String id,String periods)
    {
        this.id = id;
        this.periods = periods;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Installment{" +
                "id='" + id + '\'' +
                ", periods='" + periods + '\'' +
                '}';
    }
}
