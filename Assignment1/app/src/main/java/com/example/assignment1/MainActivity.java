package com.example.assignment1;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fbutton;
    private RecyclerView HikeList;
    private DbHelperHike DbHelperHike;
    public AdapterHike adaptHike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelperHike = new DbHelperHike(this);

        HikeList = findViewById(R.id.hikingList);
        HikeList.setLayoutManager(new LinearLayoutManager(this));
        HikeList.setHasFixedSize(true);

        //Take button in activity_main view
        fbutton = findViewById(R.id.add_hike_button);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move to add_hike view
                Intent intent = new Intent(MainActivity.this, AddEditHike.class);
                intent.putExtra("isEditMode", false);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData()
    {
        adaptHike = new AdapterHike(this,DbHelperHike.getAllHike());
        HikeList.setAdapter(adaptHike);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchHike);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchHike(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchHike(s);
                return true;
            }
        });

        return true;
    }

    private void searchHike(String query)
    {
        adaptHike = new AdapterHike(this,DbHelperHike.getSearchHike(query));
        HikeList.setAdapter(adaptHike);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteAll)
        {
            DbHelperHike.deleteAllHike();
            onResume();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}