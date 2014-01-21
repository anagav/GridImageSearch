package com.personal.ashishn.GridImageSearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;


public class ConfigurationActivity extends Activity implements Serializable {
    Spinner imageSize;
    Spinner colorFilter;
    Spinner imageType;
    EditText siteFilter;

    String imageSizeSelected = "none";
    String colorSelected = "none";
    String imageTypeSelected = "none";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        imageSize = (Spinner) findViewById(R.id.imageSizeSpinner);
        colorFilter = (Spinner) findViewById(R.id.colorSelectSpinner);
        imageType = (Spinner) findViewById(R.id.imageSelectSpinner);
        siteFilter = (EditText) findViewById(R.id.siteFilter);


        ArrayAdapter<CharSequence> imageAdapter = ArrayAdapter.createFromResource(
                this, R.array.sizeArray, R.layout.spinner_layout);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(
                this, R.array.colorArray, R.layout.spinner_layout);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
                this, R.array.typeArray, R.layout.spinner_layout);


        imageSize.setAdapter(imageAdapter);
        colorFilter.setAdapter(colorAdapter);
        imageType.setAdapter(typeAdapter);

        imageSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                imageSizeSelected = adapterView.getItemAtPosition(i).toString();
                imageSize.setSelection(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }

        );

        colorFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                colorSelected = adapterView.getItemAtPosition(i).toString();
                colorFilter.setSelection(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }

        );


        imageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                imageTypeSelected = adapterView.getItemAtPosition(i).toString();
                imageType.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }

        );


    }

    public void getCongifuration(View view) {
        Intent i = new Intent();
        i.putExtra("imageSizeSelected", imageSizeSelected);
        i.putExtra("colorSelected", colorSelected);
        i.putExtra("imageTypeSelected", imageTypeSelected);
        i.putExtra("siteFilter", siteFilter.getText());
        setResult(1, i);
        this.finish();
    }


}
