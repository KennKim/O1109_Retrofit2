package com.project.tk.o1109_retrofit2.service;

import com.project.tk.o1109_retrofit2.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by conscious on 2017-11-10.
 */

public interface GitHubService {
    public static final String URL = "https://api.github.com";

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}