package com.example.eagolukas.retrosqlite.main.ui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MainActivityTest {
    @Mock
    MainActivity activity;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void showPostTitleTestReturnPost() throws Exception {
        MainView view = Mockito.mock(MainView.class);


    }
}
