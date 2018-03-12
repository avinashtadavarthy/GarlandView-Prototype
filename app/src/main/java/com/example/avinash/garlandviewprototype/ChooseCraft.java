package com.example.avinash.garlandviewprototype;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ChooseCraft extends AppCompatActivity {


    private EditText filterText;
    private ListView itemList;
    private ArrayAdapter<String> listAdapter;

    final String [] listViewAdapterContent = {
            "Actor",
            "Actress",
            "Art Department",
            "Assistant Director",
            "Child Artist",
            "Choreographer",
            "Costumer",
            "Dancer",
            "Designer",
            "DI",
            "Dialogue Writer",
            "Director of Photography",
            "Dubbing Artist",
            "Editor",
            "Focus Puller",
            "Hair Dresser",
            "Location Manager",
            "Lyric Writer",
            "Makeup Man",
            "Mic Department",
            "Music Director",
            "Pet Supplier",
            "PRO",
            "Production Food",
            "Production Manager",
            "Script Writer",
            "Set Department",
            "SFX",
            "Side Artist",
            "Singer",
            "Sound Mixing Engineer",
            "Sound Recording Engineer",
            "Still Photographer",
            "Story Board Artist",
            "Stuntman",
            "Vehicle Driver",
            "VFX"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_craft);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.9));


        filterText = (EditText)findViewById(R.id.editText);
        itemList = (ListView)findViewById(R.id.listView);


        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listViewAdapterContent);

        itemList.setAdapter(listAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = getIntent();
                intent.putExtra("craft", listAdapter.getItem(position));
                setResult(Activity.RESULT_OK, intent);
                finish();

                Log.e("craft",listAdapter.getItem(position));

            }
        });
        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ChooseCraft.this.listAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                itemList.setVisibility(View.VISIBLE);
            }
        });

    }
}
