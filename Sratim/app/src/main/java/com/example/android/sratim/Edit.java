package com.example.android.sratim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {
    private Button OK;
    private Button Cancel;
    private EditText subject;
    private EditText body;
    private EditText url;
    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        subject = (EditText) findViewById(R.id.subject);
        subject.setText(movie.getSubject());
        body = (EditText) findViewById(R.id.body);
        body.setText(movie.getBody());
        url = (EditText) findViewById(R.id.url);
        url.setText(movie.getUrl());
        OK = (Button) findViewById((R.id.Ok));
        OK.setText("OK");
        Cancel = (Button) findViewById((R.id.Cancel));
        Cancel.setText("Cancel");
        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result(view);
            }
        };
        OK.setOnClickListener(myOnClickListener);
        Cancel.setOnClickListener(myOnClickListener);
    }
    public void Result(View view) {
        String Click = (String) ((Button) view).getText();
        switch (Click) {
            case "OK": {
                movie.setSubject(subject.getText().toString());
                movie.setBody(body.getText().toString());
                movie.setUrl(url.getText().toString());
                Intent returnIntent = getIntent();
                returnIntent.putExtra("movie", movie);
                setResult(RESULT_OK, returnIntent);
                finish();

            } break;
            case "Cancel": {
                Intent returnIntent = getIntent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();

            } break;
        }

    }

}
