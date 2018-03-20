package com.example.android.sratim;

/**
 * Created by Android on 20/03/2018.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
/**
 * Created by Anna on 3/12/2018.
 */

public abstract class CountryController  implements httpRequest.Callbacks {

    protected static ArrayList<String> Movies; // All countries.
    protected Activity activity; // The main activity.
    protected ProgressDialog progressDialog; // Progress dialog.
    protected ListView listViewMovies; // The main ListView for updating the countries list.

    // ctor:
    public CountryController(Activity activity) {
        this.activity = activity;
        listViewMovies = (ListView)activity.findViewById(R.id.listViewMovies);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Please Wait...");
    }

    // Server access is about to start - show progress dialog:
    public void onAboutToStart() {
        progressDialog.show();
    }

    // Got error from server - show toast and dismiss dialog:
    public void onError(String errorMessage) {
        Toast.makeText(activity, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }
}