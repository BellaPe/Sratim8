package com.example.android.sratim;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private MenuItem Exit;
    private MenuItem Delete;
    private MenuItem Manual;
    private MenuItem Internet;
    private MenuItem Group;
    private MenuItem Group2;
    private Database database;
    private  ArrayList<Movie> movies;
    private LinearLayout mMovieList;
    private int i;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database();
        movies = database.getAllMovies();
        mMovieList =(LinearLayout)findViewById(R.id.activity_main);
        loadMoviesDB();

      }


    private void loadMoviesDB() {
        Resources Re = getResources();
        for(i=0;i<movies.size();i++){
            int a = 1, b=2, c=3;
            RelativeLayout RL = new RelativeLayout(this);
            mMovieList.addView(RL);
            RL.setBackgroundResource(R.drawable.rectangle2);
            RL.setId(movies.get(i).get_id());
            RL.setGravity(RelativeLayout.CENTER_IN_PARENT);
            LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            RL.setLayoutParams(Params);
            RelativeLayout.LayoutParams TV1Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV2Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV3Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            TextView subject = new TextView(this);
            subject.setId(a);
            subject.setText(movies.get(i).getSubject());
            subject.setLayoutParams(TV1Params);
            TextView body = new TextView(this);
            body.setId(b);
            body.setText(movies.get(i).getBody());
            ImageView url = new ImageView(this);
            url.setId(c);
            Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(url);
            TV2Params.addRule(RelativeLayout.ALIGN_LEFT, subject.getId());
            TV2Params.addRule(RelativeLayout.ALIGN_RIGHT, subject.getId());
            TV2Params.addRule(RelativeLayout.BELOW, subject.getId());
            body.setLayoutParams(TV2Params);
            TV3Params.addRule(RelativeLayout.ALIGN_TOP, subject.getId());
            TV3Params.addRule(RelativeLayout.ALIGN_BOTTOM, body.getId());
            TV3Params.addRule(RelativeLayout.RIGHT_OF, body.getId());
            url.setLayoutParams(TV3Params);
            RL.addView(subject);
            RL.addView(body);
            RL.addView(url);
            RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Edit.class);
                    intent.putExtra("movie", movies.get(0));
                    startActivityForResult(intent, 2);
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.optionmenu, menu);
            //Menu items
            Group = menu.findItem(R.id.MainMenu);
            Group2 = menu.findItem(R.id.EditMenu);
            Exit = menu.findItem(R.id.Exit);
            Delete = menu.findItem(R.id.Delete);
            Manual = menu.findItem(R.id.Manual);
            Internet = menu.findItem(R.id.Internet);
            //Exiting the app
            Exit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                   finish();
                   return true;
                }
            });
            //Deleting all the movies
            // TODO: 14/03/2018  this

            Manual.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Intent i = new Intent(MainActivity.this, ManualNew.class);
                    startActivityForResult(i, 1);
                    return true;
                }
            });

            Internet.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Intent i = new Intent(MainActivity.this, InternetNew.class);
                    startActivityForResult(i, 1);
                    //toast
                    return true;
                }
            });




                return true;
        }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK)
        {
            Movie movie = (Movie) data.getSerializableExtra("movie");
            database.addMovie(movie);
            movies.add(movie);
            //Setting the view
            Resources Re = getResources();
            int a = 1, b=2, c=3;
            RelativeLayout RL = new RelativeLayout(this);
            mMovieList.addView(RL);
            RL.setBackgroundResource(R.drawable.rectangle2);
            RL.setId(movie.get_id());
            RL.setGravity(RelativeLayout.CENTER_IN_PARENT);
            RelativeLayout.LayoutParams TV1Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV2Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams TV3Params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            TextView subject = new TextView(this);
            subject.setId(a);
            subject.setText(movie.getSubject());
            subject.setLayoutParams(TV1Params);
            TextView body = new TextView(this);
            body.setId(b);
            body.setText(movie.getBody());
            ImageView url = new ImageView(this);
            url.setId(c);
            Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(url);
            TV2Params.addRule(RelativeLayout.ALIGN_LEFT, subject.getId());
            TV2Params.addRule(RelativeLayout.ALIGN_RIGHT, subject.getId());
            TV2Params.addRule(RelativeLayout.BELOW, subject.getId());
            body.setLayoutParams(TV2Params);
            TV3Params.addRule(RelativeLayout.ALIGN_TOP, subject.getId());
            TV3Params.addRule(RelativeLayout.ALIGN_BOTTOM, body.getId());
            TV3Params.addRule(RelativeLayout.RIGHT_OF, body.getId());
            url.setLayoutParams(TV3Params);
            RL.addView(subject);
            RL.addView(body);
            RL.addView(url);


            Toast.makeText(MainActivity.this, "Movie added",
                    Toast.LENGTH_LONG).show();

        }

        else if(requestCode==2&&resultCode==RESULT_OK){
            Movie movie = (Movie) data.getSerializableExtra("movie");
            database.updateMovie(movie);
        }
    }



        //adding event listener to the ListView
     //   MyListView.setOnItemClickListener(
                //On item click we will show to the screen a popup with the item's content
             //   new AdapterView.OnItemClickListener(){
            //        @Override
               //     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

      //              }

            //    }
    //    );
 //   }

}