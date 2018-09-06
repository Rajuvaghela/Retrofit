package com.example.raju.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.raju.retrofit.ApiInterface;
import com.example.raju.retrofit.R;
import com.example.raju.retrofit.adapter.MoviesAdapter;
import com.example.raju.retrofit.bean.LoginBean;
import com.example.raju.retrofit.bean.LoginStatusBean;
import com.example.raju.retrofit.bean.Movie;
import com.example.raju.retrofit.bean.MoviesResponse;
import com.example.raju.retrofit.retrofit.ApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "ec01f8c2eb6ac402f2ca026dc2d9b8fd";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("EmailId", "raju@gmail.com");
        params.put("Password", "123");
        params.put("Tokan", "1");
        params.put("DeviceType", "1");
        params.put("DeviceName", "1");
        Call<LoginBean> call = apiService.getLoginDetail(params);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Log.e("res sucess", new Gson().toJson(response.body()));
                } else {
                    Log.e("res", new Gson().toJson(response.errorBody()));
                }

                List<LoginStatusBean> loginStatusBeans = response.body().Pepole_Login;
                for (int i = 0; i < loginStatusBeans.size(); i++) {
                    Log.e("Status_Message+++" + i, loginStatusBeans.get(i).Status_Message);
                }

            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                // Log error here since request failed
                Log.e("error", t.toString());
            }
        });

        /*ApiInterface apiService2 =
                ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call2 = apiService2.getTopRatedMovies(API_KEY);
        call2.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                if (response.isSuccessful())
                    Log.e("res", new Gson().toJson(response.body()));
                else
                    Log.e("res", new Gson().toJson(response.errorBody()));
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });*/
    }
   /* private void sendFirebaseTokenToServer(final String firebaseToken) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("token", firebaseToken);
            jsonObject.put("type", "1");
            jsonObject.put("userid", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String str = jsonObject.toString();
        Call<BeanSendToken> call = apiService.sendFirebaseTokenResponse(str);
        call.enqueue(new Callback<BeanSendToken>() {
            @Override
            public void onResponse(Call<BeanSendToken> call, Response<BeanSendToken> response) {
                if (response.isSuccessful()) {
                    Log.e("token res sucess", new Gson().toJson(response.body()));
                    sharePreferences.setFirebaseToken(firebaseToken);
                } else {
                    Log.e("token res", new Gson().toJson(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<BeanSendToken> call, Throwable t) {
                Log.e("send token error", t.toString());
            }
        });
    }*/
}