package com.example.eagolukas.retrosqlite.main.ui;

import com.example.eagolukas.retrosqlite.retrof.MInterface;
import com.example.eagolukas.retrosqlite.retrof.Post;
import com.example.eagolukas.retrosqlite.sqlite.DatabaseHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

public class MainPresenterTest {

    @Mock
    MainPresenter presenter ;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAttachView() throws Exception {
        MainView view = Mockito.mock(MainView.class);

        Mockito.doCallRealMethod().when(presenter).attachView(view);
        Mockito.doCallRealMethod().when(presenter).getView();

        presenter.attachView(view);
        Assert.assertNotNull(presenter.getView());
    }

    @Test
    public void testDetachView() throws Exception {
        MainView view = Mockito.mock(MainView.class);

        Mockito.doCallRealMethod().when(presenter).attachView(view);
        Mockito.doCallRealMethod().when(presenter).getView();
        Mockito.doCallRealMethod().when(presenter).detachView();

        presenter.attachView(view);
        presenter.detachView();
        Assert.assertNull(presenter.getView());
    }

    @Test
    public void testWritePost() throws Exception {
        DatabaseHandler db = Mockito.mock(DatabaseHandler.class);
        Mockito.doCallRealMethod().when(presenter).writePost(); // nevraci
        Mockito.when(presenter.createDBHandler()).thenReturn(db); ///vraci
        Mockito.doNothing().when(db).addPost(Mockito.any(Post.class));

        presenter.writePost();
        Mockito.verify(presenter).createDBHandler();
        Mockito.verify(db).addPost(Mockito.any(Post.class));
    }

    @Test
    public void testReadPosts() throws Exception {
        DatabaseHandler db = Mockito.mock(DatabaseHandler.class);
        Mockito.doCallRealMethod().when(presenter).readPosts(); // nevraci
        Mockito.when(presenter.createDBHandler()).thenReturn(db); ///vraci
        Mockito.when(db.getPosts()).thenReturn(new ArrayList<Post>());

        presenter.readPosts();
        Mockito.verify(presenter).createDBHandler();
        Mockito.verify(db).getPosts();

        MainView view = Mockito.mock(MainView.class);
        Mockito.when(presenter.getView()).thenReturn(view);

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post());
        Mockito.when(db.getPosts()).thenReturn(posts);
        presenter.readPosts();
        Mockito.verify(db,Mockito.times(2)).getPosts();
        Mockito.verify(view).showPostTitlePlusID(posts.get(0));
    }

    @Test
    public void testDownloadPosts() throws Exception {
        Retrofit mokRetrofit = Mockito.mock(Retrofit.class);
        Mockito.when(presenter.getAdapter()).thenReturn(mokRetrofit);

        MInterface mokMInterface = Mockito.mock(MInterface.class);
        Mockito.when(mokRetrofit.create(MInterface.class)).thenReturn(mokMInterface);

        Call mokCall = Mockito.mock(Call.class);
        Mockito.when(mokMInterface.getUser()).thenReturn(mokCall);
        Mockito.doNothing().when(mokCall).enqueue(Mockito.any(Callback.class));

        Mockito.doCallRealMethod().when(presenter).downloadPosts();
        presenter.downloadPosts();
    }
}