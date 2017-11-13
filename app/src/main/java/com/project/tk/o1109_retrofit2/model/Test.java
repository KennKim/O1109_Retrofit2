package com.project.tk.o1109_retrofit2.model;

public class Test {

    public String no;
    public String inserted_date;
    public String loc_comment;
    public String loc_imgpath;
    public String loc_thumbpath;
    public String loc_date_reg;
    public String loc_date_update;
    public String m_id;

    public Test(String loc_no, String loc_name, String loc_comment, String loc_imgpath, String loc_thumbpath, String loc_date_reg, String loc_date_update, String m_id) {
        this.no = loc_no;
        this.inserted_date = loc_name;
        this.loc_comment = loc_comment;
        this.loc_imgpath = loc_imgpath;
        this.loc_thumbpath = loc_thumbpath;
        this.loc_date_reg = loc_date_reg;
        this.loc_date_update = loc_date_update;
        this.m_id = m_id;
    }
}
