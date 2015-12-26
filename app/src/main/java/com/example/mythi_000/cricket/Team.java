package com.example.mythi_000.cricket;

/**
 * Created by mythi_000 on 12/10/2015.
 */
public class Team {
    private String no,name;

    public Team(String no, String name)
    {
        this.setNo(no);
        this.setName(name);

    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
