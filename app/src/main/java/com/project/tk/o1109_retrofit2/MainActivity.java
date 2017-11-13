package com.project.tk.o1109_retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.tk.o1109_retrofit2.model.Contributor;
import com.project.tk.o1109_retrofit2.model.Parse;
import com.project.tk.o1109_retrofit2.model.Repo;
import com.project.tk.o1109_retrofit2.model.Test;
import com.project.tk.o1109_retrofit2.service.ApiService;
import com.project.tk.o1109_retrofit2.service.GitHub;
import com.project.tk.o1109_retrofit2.service.GitHubService;
import com.project.tk.o1109_retrofit2.service.MyInterface;
import com.project.tk.o1109_retrofit2.service.MyParse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String MyLog = "mytest";

    private TextView tv1;
    private EditText et1, et2;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call2();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call3();
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCall();
            }
        });
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myParse();
            }
        });
        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myParsePost();
            }
        });
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        progress = (ProgressBar) findViewById(R.id.progress);

    }

    private void myParsePost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyParse.MY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyParse myParse = retrofit.create(MyParse.class);
        Call<Parse> call = myParse.getPostRate(et1.getText().toString(), et2.getText().toString());
        progress.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Parse>() {
            @Override
            public void onResponse(Call<Parse> call, Response<Parse> response) {
                Parse p = response.body();
                tv1.append(p.from + " ");
                tv1.append(p.rate + " ");
                tv1.append(p.to + " ");
                tv1.append(p.utc + " ");
                tv1.append(p.serverTime);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Parse> call, Throwable t) {
                tv1.setText("fail");
                progress.setVisibility(View.GONE);

            }
        });
    }

    private void myParse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyParse.MY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyParse myParse = retrofit.create(MyParse.class);
        Call<Parse> call = myParse.myRate();
        call.enqueue(new Callback<Parse>() {
            @Override
            public void onResponse(Call<Parse> call, Response<Parse> response) {
                Parse p = response.body();
                tv1.append(p.rate);
                tv1.append(p.utc);
                tv1.append(p.serverTime);
            }

            @Override
            public void onFailure(Call<Parse> call, Throwable t) {
                tv1.setText("fail");

            }
        });
//        call.enqueue(new Callback<Parse>() {
//            @Override
//            public void onResponse(Call<List<Parse>> call, Response<List<Parse>> response) {
//                List<Parse> m = response.body();
//                for (Parse p : m) {
//
//                    Log.v(MyLog, p.rate);
//                    tv1.setText(p.rate);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Parse>> call, Throwable t) {
//                Log.v(MyLog, "onFailure!");
//            }
//        });
    }

    private void myCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyInterface.MY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyInterface myInterface = retrofit.create(MyInterface.class);
        Call<List<Test>> call = myInterface.myRate();
        call.enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                Log.v(MyLog, response.body().toString());
                List<Test> myTest = response.body();
                for (Test t : myTest) {
                    tv1.append(t.no);
                    tv1.append("-");
                    tv1.append(t.inserted_date);
                    tv1.append("-");
                }
            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {
                Log.v(MyLog, "onFailure!");

            }
        });


    }

    private void call3() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // GitHub API 인터페이스 생성
        GitHub gitHub = retrofit.create(GitHub.class);
        // 인터페이스에 구현한 메소드인 contributors에 param 값을 넘기는 요청 만ㄷ름
        Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");

        // 앞서만든 요청을 수행
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            // 성공시
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                Log.v(MyLog, response.body().toString());
                List<Contributor> contributors = response.body();
                // 받아온 리스트를 순회하면서
                for (Contributor contributor : contributors) {
                    // 텍스트 뷰에 login 정보를 붙임
                    tv1.append(contributor.login);
                    tv1.append(" - ");
                }
            }

            @Override
            // 실패시
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                Log.v(MyLog, "onFailure!");

            }
        });

    }

    private void call2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> comment = apiService.getComment(2);
        comment.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    //처음에 배열이므로
                    Log.v(MyLog, result);
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        int postId;
                        int id;
                        String name;
                        String mail;
                        String body;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //배열이므로 0 번을 가져와보자
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            postId = jsonObject.getInt("postId");
                            id = jsonObject.getInt("id");
                            name = jsonObject.getString("name");
                            mail = jsonObject.getString("email");
                            body = jsonObject.getString("body");
                            Log.v(MyLog, jsonObject.toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        /*comment.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v(MyLog, "success!");

                try {
                    Log.v(MyLog, response.body().string());
                    tv1.setText(response.body().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v(MyLog, "failure : Check your internet");

            }
        });*/
    }

    private void call() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("octocat");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.v(MyLog, "success!");
                if (response.isSuccessful()) {
                    Log.v("mytest", response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.v(MyLog, "onFailure!");

            }
        });
    }
}
