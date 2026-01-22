package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AlertDialog;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private void showEditDialog(String currentCity, int position) {
        final EditText input = new EditText(this);
        input.setText(currentCity);
        input.setSelection(currentCity.length());

        new AlertDialog.Builder(this)
                .setTitle("Edit City")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String updatedCity = input.getText().toString().trim();

                    if (!updatedCity.isEmpty()) {
                        dataList.set(position, updatedCity);
                        cityAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private ArrayList<String> dataList;
    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Moscow",
                "Sydney", "Berlin", "Vienna",
                "Tokyo", "Beijing", "Osaka", "New Delhi"
        };

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityList = findViewById(R.id.city_list);
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCity = dataList.get(position);
            showEditDialog(selectedCity, position);
        });
    }
}