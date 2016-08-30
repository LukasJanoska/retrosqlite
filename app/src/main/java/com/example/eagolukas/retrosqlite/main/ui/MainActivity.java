package com.example.eagolukas.retrosqlite.main.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eagolukas.retrosqlite.R;
import com.example.eagolukas.retrosqlite.retrof.Post;
import com.example.eagolukas.retrosqlite.sqlite.DatabaseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity implements MainView {

    DatabaseHandler db;
    @BindView(R.id.txt_comp) TextView textV1;
    @BindView(R.id.txt_comp2) TextView textV2;
    @BindView(R.id.txt_comp3) TextView textV3;

    MainPresenter mainPresenter;

    @Override
    protected void onStop() {
        mainPresenter.detachView();
        super.onStop();
    }

    @Override
    protected void onStart() {
        mainPresenter.attachView(this);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(getApplicationContext());
        mainPresenter.downloadPosts();
    }

    @OnClick(R.id.buttonWrite)
    public void OnClicWrite(View v){
        mainPresenter.writePost();
    }

    @OnClick(R.id.buttonRead)
    public void OnClicRead(View v)  {
        try {
            mainPresenter.readPosts();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPostTitle(Post p) {
        textV1.setText("post :"+p.getTitle());
    }

    @Override
    public void showPostId(Post p) {
        textV2.setText("id :"+p.getId());
    }

    @Override
    public void showPostTitlePlusID(Post p) {
        textV3.setText("post : "+p.getId() +" "+ p.getTitle());
    }
}
