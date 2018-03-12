package com.example.avinash.garlandviewprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestTickle;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.VolleyTickle;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class GarlandViewMain extends android.support.v4.app.Fragment {

    View myView;

    final static public int REQUEST_CRAFT_CHANGE = 2673;

    //declaration portion
    RecyclerView horizontalRecycler;
    HorizontalAdapter horizontalAdapter;
    RecyclerViewClickListener listener;


    private String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlZlbHVwcGFhQG5kaWFuLmNvbSIsImlkIjoiNWE2YWQzNzZiODdmZGMxY2RhYTgxMmQ3IiwiaXNDbGllbnQiOnRydWUsImNhdGVnb3J5IjoiQ0xJRU5UIiwiaWF0IjoxNTE2OTUwNDA1LCJleHAiOjE1MTcwMzY4MDV9.J9DdgjQMn-OB1qu8OTc41kJlq0BhdnBKXW-odcVoDQc";

    private String[] who = new String[] {
            "Actor", "Actress", "Artdepartment", "Assistantdirector", "Childartist", "Choreographer", "Costumer", "Dancer", "Designer", "Di",
            "Dialoguewriter", "Directorofphotography", "Dubbingartist", "Editor", "Focuspuller", "Hairdresser", "Locationmanager", "Lyricwriter",
            "Makeupman", "Micdepartment", "Musicdirector", "Petsupplier", "Pro", "Productionfood", "Productionmanager", "Scriptwriter", "Setdepartment",
            "Sfx", "Sideartist", "Singer", "Soundmixingengineer", "Soundrecordingengineer", "Stillphotographer", "Storyboardartist", "Stuntman",
            "Vehicledriver", "Vfx"
    };

    private String[] who1 = new String[] {
            "Actor", "Actress", "Art Department", "Assistant Director", "Child Artist", "Choreographer", "Costumer", "Dancer", "Designer", "DI", "Dialogue Writer",
            "Director of Photography", "Dubbing Artist", "Editor", "Focus Puller", "Hair Dresser", "Location Manager", "Lyric Writer", "Makeup Man", "Mic Department",
            "Music Director", "Pet Supplier", "PRO", "Production Food", "Production Manager", "Script Writer", "Set Department", "SFX", "Side Artist", "Singer",
            "Sound Mixing Engineer", "Sound Recording Engineer", "Still Photographer", "Story Board Artist", "Stuntman", "Vehicle Driver", "VFX"
    };

    private String[] craftinfo = who;


    int i = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.garlandviewmain,container,false);

        horizontalRecycler = (RecyclerView) myView.findViewById(R.id.outer_recyclerview);

        listener = new RecyclerViewClickListener() {
            @Override
            public void onCLick(View view, int position) {

                Intent i = new Intent(getActivity().getApplicationContext(), ChooseCraft.class);
                startActivityForResult(i, REQUEST_CRAFT_CHANGE);

            }
        };


        fetchData1("http://24crafts.cf:3000/user/view/all/" + who[i]);

        return myView;
    }






    void fetchData1(String url) {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                craftinfo[i] = response;

                if(i < who.length - 1) {
                    i++;
                    fetchData1("http://24crafts.cf:3000/user/view/all/" + who[i]);
                } else {
                    horizontalAdapter = new HorizontalAdapter(getActivity().getApplicationContext(), who1, craftinfo, listener);
                    LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    horizontalRecycler.setLayoutManager(horizontalLayoutManager);
                    SnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(horizontalRecycler);

                    horizontalRecycler.setAdapter(horizontalAdapter);

                    horizontalRecycler.setHasFixedSize(true);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNpbmdhbTY5QGdtYWlsLmNvbSIsImlkIjoiNWE5ZDNmYmM3N2E3NWYwYWY3MjQ0NmNhIiwiaXNTdWJzY3JpYmVkIjpmYWxzZSwiaXNDbGllbnQiOmZhbHNlLCJjYXRlZ29yeSI6IkNSQUZUU01BTiIsImlhdCI6MTUyMDg0MTIyNCwiZXhwIjoxNTIwOTI3NjI0fQ.kmZVY-gJIxfbhKDzjnzzO5BlaoNtFFfNadXTZpekS3I");

                return params;
            }
        };

        NetworkController.getInstance().addToRequestQueue(request);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CRAFT_CHANGE) {
            if(resultCode == Activity.RESULT_OK) {

                String craftchosen = data.getStringExtra("craft");

                switch(craftchosen) {
                    case "Actor" : horizontalRecycler.scrollToPosition(0); break;
                    case "Actress" : horizontalRecycler.scrollToPosition(1); break;
                    case "Art Department" : horizontalRecycler.scrollToPosition(2); break;
                    case "Assistant Director" : horizontalRecycler.scrollToPosition(3); break;
                    case "Child Artist" : horizontalRecycler.scrollToPosition(4); break;
                    case "Choreographer" : horizontalRecycler.scrollToPosition(5); break;
                    case "Costumer" : horizontalRecycler.scrollToPosition(6); break;
                    case "Dancer" : horizontalRecycler.scrollToPosition(7); break;
                    case "Designer" : horizontalRecycler.scrollToPosition(8); break;
                    case "DI" : horizontalRecycler.scrollToPosition(9); break;
                    case "Dialogue Writer" : horizontalRecycler.scrollToPosition(10); break;
                    case "Director of Photography" : horizontalRecycler.scrollToPosition(11); break;
                    case "Dubbing Artist" : horizontalRecycler.scrollToPosition(12); break;
                    case "Editor" : horizontalRecycler.scrollToPosition(13); break;
                    case "Focus Puller" : horizontalRecycler.scrollToPosition(14); break;
                    case "Hair Dresser" : horizontalRecycler.scrollToPosition(15); break;
                    case "Location Manager" : horizontalRecycler.scrollToPosition(16); break;
                    case "Lyric Writer" : horizontalRecycler.scrollToPosition(17); break;
                    case "Makeup Man" : horizontalRecycler.scrollToPosition(18); break;
                    case "Mic Department" : horizontalRecycler.scrollToPosition(19); break;
                    case "Music Director" : horizontalRecycler.scrollToPosition(20); break;
                    case "Pet Supplier" : horizontalRecycler.scrollToPosition(21); break;
                    case "PRO" : horizontalRecycler.scrollToPosition(22); break;
                    case "Production Food" : horizontalRecycler.scrollToPosition(23); break;
                    case "Production Manager" : horizontalRecycler.scrollToPosition(24); break;
                    case "Script Writer" : horizontalRecycler.scrollToPosition(25); break;
                    case "Set Department" : horizontalRecycler.scrollToPosition(26); break;
                    case "SFX" : horizontalRecycler.scrollToPosition(27); break;
                    case "Side Artist" : horizontalRecycler.scrollToPosition(28); break;
                    case "Singer" : horizontalRecycler.scrollToPosition(29); break;
                    case "Sound Mixing Engineer" : horizontalRecycler.scrollToPosition(30); break;
                    case "Sound Recording Engineer" : horizontalRecycler.scrollToPosition(31); break;
                    case "Still Photographer" : horizontalRecycler.scrollToPosition(32); break;
                    case "Story Board Artist" : horizontalRecycler.scrollToPosition(33); break;
                    case "Stuntman" : horizontalRecycler.scrollToPosition(34); break;
                    case "Temporary Vehicle Driver" : horizontalRecycler.scrollToPosition(35); break;
                    case "VFX" : horizontalRecycler.scrollToPosition(36); break;
                }


            }
        }
    }
}
