package com.app.youtube.activityYoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.app.youtube.R;
import com.app.youtube.adapter.AdapterMain;
import com.app.youtube.api.YoutubeService;
import com.app.youtube.helper.RecyclerItemClickListener;
import com.app.youtube.helper.RetrofitConfig;
import com.app.youtube.helper.YoutubeConfig;
import com.app.youtube.model.Item;
import com.app.youtube.model.Resultado;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewYoutube;
    AdapterMain adapterMain;
    MaterialSearchView searchView;
    List<Item> listVideos = new ArrayList<>();
    Resultado resultado;

//    YouTubePlayerView youTubePlayerView;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // config retrofit2
        retrofit = RetrofitConfig.getRetrofit();

        //method config toolbar
        configToolBar();

        //method recupera os videos
        getVideos("");

        //method config searshView
        configSearshView();
    }

    //config toolbar
    private void configToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar( toolbar );
    }

    //config recyclerView
    private void configRecyclerView() {
        //adapter para a listRecycler videos
        adapterMain = new AdapterMain(this, listVideos);

        recyclerViewYoutube = findViewById(R.id.listVideoRecycler);
        recyclerViewYoutube.setHasFixedSize(true);
        recyclerViewYoutube.setLayoutManager( new LinearLayoutManager(this));
        recyclerViewYoutube.setAdapter( adapterMain );

        // config click recycler
        recyclerViewYoutube.addOnItemTouchListener( new RecyclerItemClickListener(
                this, recyclerViewYoutube,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Item video = listVideos.get(position);
                        String idVideo = video.id.videoId;

                        Intent i = new Intent(MainActivity.this, PlayerActivity.class);
                        i.putExtra("id", idVideo);
                        startActivity(i);
                    }
                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));
    }

    // recupera os videos
    private void getVideos(String searchTxt) {
        String q = searchTxt.replaceAll("","+");

        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        youtubeService.getVideos("snippet", "date", "20",
                YoutubeConfig.API_KEY,
                YoutubeConfig.CANAL_ID,
                q
                ).enqueue(new Callback<Resultado>() {

            @Override
            public void onResponse(@NonNull Call<Resultado> call, @NonNull Response<Resultado> response) {
                Log.d("tag", "onResponse: "+response);
                if( response.isSuccessful() ){
                    resultado = response.body();
                    assert resultado != null;
                    
                    listVideos = resultado.items;
               //method config recyclerView
                    configRecyclerView();

                    Log.d("tag-2", "Result: "+resultado.items.get(0).id.videoId );
                }

            }
            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });
    }

    // inflar o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pesquisa, menu);
        MenuItem item = menu.findItem(R.id.searsh_menu);

        searchView.setMenuItem(item);
        return true;
    }

    // config searshView
    private void configSearshView() {
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getVideos( s );
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
       searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }
            @Override
            public void onSearchViewClosed() {
                getVideos("");
            }
        });

    }
}