package com.sprint.sprint.model;

import java.util.concurrent.atomic.AtomicLong;

public class GDP
{
    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private static String country;
    private static String gdp;

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

    public static String getCountry()
    {
        return country;
    }

    public static void setCountry(String country)
    {
        GDP.country = country;
    }

    public static String getGdp()
    {
        return gdp;
    }

    public static void setGdp(String gdp)
    {
        GDP.gdp = gdp;
    }

    @Override
    public String toString()
    {
        return "GDP: " + ' ' + country + ' ' + gdp;
    }
}
