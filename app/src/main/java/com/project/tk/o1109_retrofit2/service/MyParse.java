package com.project.tk.o1109_retrofit2.service;

import com.project.tk.o1109_retrofit2.model.Parse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by conscious on 2017-11-12.
 */

public interface MyParse {
    public static final String MY_URL = "http://jip.dothome.co.kr";
    public static final String SUB_URL = "/parse_rate/parse.php";


    // GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
    @GET(SUB_URL)
    Call<Parse> myRate();


    @GET(SUB_URL)
    Call<Parse>getPostRate(@Query("from") String from,@Query("to") String to);


}
