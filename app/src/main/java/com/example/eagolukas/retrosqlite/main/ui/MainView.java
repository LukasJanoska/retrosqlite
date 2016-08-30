package com.example.eagolukas.retrosqlite.main.ui;

import com.example.eagolukas.retrosqlite.retrof.Post;

public interface MainView {
    void showPostId(Post p);
    void showPostTitle(Post p);
    void showPostTitlePlusID(Post p);
}
