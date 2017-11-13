package com.project.tk.o1109_retrofit2.service;

import com.project.tk.o1109_retrofit2.model.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by conscious on 2017-11-12.
 */

public interface MyInterface {
    public static final String MY_URL = "http://jip.dothome.co.kr";


    // GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
    @GET("/myhome/test/rate/myrate.php")
    // JSON Array를 리턴하므로 List<>가 되었다
    Call<List<Test>> myRate();


}
