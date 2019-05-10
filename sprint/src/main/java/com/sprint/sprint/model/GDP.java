package com.sprint.sprint.model;

import java.util.concurrent.atomic.AtomicLong;

public class GDP
{
    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private String country;
    private String gdp;

    public GDP(String country, String gdp)
    {
        this.id = counter.incrementAndGet();
        this.country = country;
        this.gdp = gdp;
    }

    public GDP(GDP toClone)
    {
        this.id = toClone.getId();
        this.country = getCountry();
        this.gdp = getGdp();
    }

    public static AtomicLong getCounter()
    {
        return counter;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getGdp()
    {
        return gdp;
    }

    public void setGdp(String gdp)
    {
        this.gdp = gdp;
    }

    @Override
    public String toString()
    {
        return "GDP: " + ' ' + country + ' ' + gdp;
    }
}
