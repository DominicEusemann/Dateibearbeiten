package com.example.dominic.dateibearbeiten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class dateiBearbeiten extends AppCompatActivity implements Button.OnClickListener {

    private Button buttonDelete;
    private Button buttonSave;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datei_bearbeiten);
        try {
            FileOutputStream fileOutputStream = openFileOutput("dateiBearbeiten_projekt.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        buttonDelete = (Button) findViewById(R.id.buttondelete);
        buttonSave = (Button) findViewById(R.id.buttonsave);
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.edittext);
        buttonDelete.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == buttonSave.getId()){
            try {
                FileOutputStream fileOutputStream = openFileOutput("dateiBearbeiten_projekt.txt", MODE_APPEND);
                OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
                writer.write(editText.getText().toString());
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder stringBuilder = new StringBuilder();
            try {
                FileInputStream fileInputStream = openFileInput("dateiBearbeiten_projekt.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                textView.setSingleLine(false);
                textView.setText(stringBuilder.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else{
            try {
                FileOutputStream fileOutputStream = openFileOutput("dateiBearbeiten_projekt.txt", MODE_PRIVATE);

                OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
                deleteFile("dateiBearbeiten_projekt.txt");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textView.setText(null);
        }
    }
}
