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

    String imageSizeSelected = "extra-large";
    String colorSelected = "none";
    String imageTypeSelected = "none";

    Bundle outState = new Bundle();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.outState.putBundle("state", outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        outState = savedInstanceState.getBundle("state");
        assert outState != null;
        imageSize.setSelection(outState.getInt("imageSize"));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        imageSize = (Spinner) findViewById(R.id.imageSizeSpinner);
        //imageSize.setSelection((savedInstanceState.containsKey("imageSize")) ? savedInstanceState.getInt("imageSize") : 0);
        colorFilter = (Spinner) findViewById(R.id.colorSelectSpinner);
        //imageSize.setSelection((savedInstanceState.containsKey("colorSelected")) ? savedInstanceState.getInt("colorSelected") : 0);
        imageType = (Spinner) findViewById(R.id.imageSelectSpinner);
        //imageSize.setSelection((savedInstanceState.containsKey("imageType")) ? savedInstanceState.getInt("imageType") : 0);
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
                outState.putInt("imageSize", i);
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
                outState.putInt("colorSelected", i);
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
                outState.putInt("imageType", i);
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
