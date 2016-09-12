package com.mac.training.recyclerviewtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mac.training.recyclerviewtest.model.Movie;
import com.mac.training.recyclerviewtest.adapter.MoviesAdapter;
import com.mac.training.recyclerviewtest.R;
import com.mac.training.recyclerviewtest.RecyclerTouchListener;
import com.mac.training.recyclerviewtest.model.MovieResponse;
import com.mac.training.recyclerviewtest.rest.ApiClient;
import com.mac.training.recyclerviewtest.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LOG = "superlog";
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = ((RecyclerView) findViewById(R.id.recycler_view));

        //moviesAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // set the adapter
//        recyclerView.setAdapter(moviesAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
    }

    private void prepareMovieData() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();
                movieList = response.body().getResults();
                Log.d(TAG_LOG, "Number of movies received: " + movieList.size());
                moviesAdapter = new MoviesAdapter(movieList);
                recyclerView.setAdapter(moviesAdapter);
                //moviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG_LOG, t.toString(), t);
            }
        });


        //moviesAdapter.notifyDataSetChanged();
    }

    //menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        RecyclerView.LayoutManager layoutManager = null;
        switch (id) {
            case R.id.llmh:
                // Setup layout manager for items with orientation
                // Also supports `LinearLayoutManager.HORIZONTAL`
                layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case R.id.llmv:
                layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                break;
            case R.id.sglm:
                // First param is number of columns and second param is orientation i.e Vertical or Horizontal
                layoutManager =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                break;
            case R.id.glm:
                layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                break;
            default:
                break;
        }

        // Optionally customize the position you want to default scroll to
        layoutManager.scrollToPosition(0);
        // Attach layout manager to the RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        return super.onOptionsItemSelected(item);
    }

}
