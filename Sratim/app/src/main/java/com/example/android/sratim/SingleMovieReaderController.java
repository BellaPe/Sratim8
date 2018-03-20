package com.example.android.sratim;

/**
 * Created by Android on 20/03/2018.
 */
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Class for reading server data:
public class SingleMovieReaderController extends CountryController {


    String MovieName="";
    // ctor:
    public SingleMovieReaderController(Activity activity,String countryName) {

        super(activity);
        this.MovieName = countryName;

    }

    // Read all countries from the server:
    public void readSingleCountry() {
        httpRequest httpRequest = new httpRequest(this);
        httpRequest.execute("https://api.themoviedb.org/3/search/movie?api_key=4c9685f64a32ab29908d264dbffbbda6&language=en-US&query="+str+"page=1&include_adult=false");
    }

    // Got country from the server - update in activity:
    public void onSuccess(String downloadedText) {

        try {

            // Translate all to a JSON array:
            JSONArray jsonArray = new JSONArray(downloadedText);

            if(jsonArray!=null && jsonArray.length()==1){
                JSONObject jsonObj=jsonArray.getJSONObject(0);
                FullCountryInfo countryInfo=new FullCountryInfo(jsonObj);

                int[] idArray={
                        R.id.country_name,
                        R.id.time_zone,
                        R.id.capital,
                        R.id.region,
                        R.id.population,
                        R.id.native_name,
                        R.id.calling_code
                };

                String[] valArray={
                        countryInfo.getName(),
                        countryInfo.getTimezones()
                                .replace("\"","")
                                .replace("[","")
                                .replace("]",""),
                        countryInfo.getCapital(),
                        countryInfo.getRegion(),
                        countryInfo.getPopulation(),
                        countryInfo.getNativeName(),
                        countryInfo.getCallingCodes()
                                .replace("\"","")
                                .replace("[","")
                                .replace("]","")
                };


                for(int i=0; i<idArray.length;i++){
                    TextView txt=activity.findViewById(idArray[i]);
                    txt.setText(valArray[i]);
                }


            }
        }
        catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Dismiss dialog:
        progressDialog.dismiss();
    }
}