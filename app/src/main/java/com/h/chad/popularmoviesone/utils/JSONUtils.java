package com.h.chad.popularmoviesone.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.h.chad.popularmoviesone.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;


/**
 * Created by chad on 5/8/2017.
 */

public final class JSONUtils {

    private final static String LOG_TAG = JSONUtils.class.getName();

    public static ArrayList<Movie> getSimpleStringFromJson(Context context, String jsonString)
            throws JSONException{

        ArrayList<Movie> movieArrayList= new ArrayList<>();
        if(TextUtils.isEmpty(jsonString)){
            return movieArrayList;
        }
        try{
            JSONObject baseResponse = new JSONObject(jsonString);
            //Log.i(LOG_TAG, "Chad says: " + baseResponse);
            JSONArray resultsArray = null;
            if(baseResponse.has("results")){
                resultsArray = baseResponse.getJSONArray("results");
            }else{
                return movieArrayList;
            }

            for(int i = 0; i < resultsArray.length(); i++){

                JSONObject movie = resultsArray.getJSONObject(i);
                int movieID = movie.getInt("id");
                String title = movie.getString("title");
                String releaseDate = movie.getString("release_date");
                String posterPath = movie.getString("poster_path");
                int voteCount = movie.getInt("vote_count");
                double voteAverage = movie.getDouble("vote_average");
                double popularity = movie.getDouble("popularity");
                String plot = movie.getString("overview");
                Movie newMovie = new Movie(movieID, title, releaseDate, posterPath,
                        voteCount, voteAverage, popularity, plot);
                movieArrayList.add(newMovie);

            }
        }catch (JSONException e){
            e.printStackTrace();
            Log.e(LOG_TAG, "Error Parsing JSON");
        }
        return movieArrayList;
    }
}
