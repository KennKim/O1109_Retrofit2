package com.project.tk.o1109_retrofit2.model;

/**
 * Created by conscious on 2017-11-12.
 */

public class Parse {

    public String from;
    public String to;
    public String rate;
    public String utc;
    public String serverTime;

    public Parse(String from, String to, String rate, String utc, String serverTime) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.utc = utc;
        this.serverTime = serverTime;
    }
}
