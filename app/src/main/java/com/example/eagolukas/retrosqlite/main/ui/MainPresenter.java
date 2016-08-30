package com.example.eagolukas.retrosqlite.main.ui;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.espresso.IdlingResource;

import com.example.eagolukas.retrosqlite.retrof.MInterface;
import com.example.eagolukas.retrosqlite.retrof.Post;
import com.example.eagolukas.retrosqlite.sqlite.DatabaseHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter {

    private Context c;
    public final static String URL = "http://jsonplaceholder.typicode.com";
    private MainView mainView;
    Post post;

    public MainPresenter(Context c) {
        this.c = c;
    }

    public void attachView(MainView mainView){
        this.mainView = mainView;
    }

    public MainView getView(){
        return mainView;
    }

    public void detachView(){
        this.mainView = null;
    }

    public DatabaseHandler createDBHandler(){
        return new DatabaseHandler(c);
    }

    public void writePost(){
        DatabaseHandler db = createDBHandler();
        db.addPost(post);
    }

    public void readPosts() throws InterruptedException {
        DatabaseHandler db = createDBHandler();
        List<Post> posts = db.getPosts();

        for (Post cn : posts) {
            getView().showPostTitlePlusID(cn);
        }
    }

    public Retrofit getAdapter(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void downloadPosts(){
        Retrofit retrofit = getAdapter();

        MInterface service = retrofit.create(MInterface.class);
        service.getUser().enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                post = response.body();
                getView().showPostTitle(post);
                getView().showPostId(post);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                //String err = error.getMessage();

            }
        });
    }

/*    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isIdleNow() {
        return false;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        callbacks.add(resourceCallback);
    }*/
}
