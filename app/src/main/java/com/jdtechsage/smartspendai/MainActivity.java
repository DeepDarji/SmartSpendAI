package com.jdtechsage.smartspendai;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int VOICE_INPUT_REQ_CODE = 1;

    Button btnVoiceInput;
    TextView txtResult;
    ListView listExpenses;

    ArrayList<String> expenses = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVoiceInput = findViewById(R.id.btnVoiceInput);
        txtResult = findViewById(R.id.txtResult);
        listExpenses = findViewById(R.id.listExpenses);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenses);
        listExpenses.setAdapter(adapter);

        btnVoiceInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        try {
            startActivityForResult(intent, VOICE_INPUT_REQ_CODE);
        } catch (Exception e) {
            txtResult.setText("Voice input not supported on your device.");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_INPUT_REQ_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = result.get(0);

            txtResult.setText(spokenText);
            expenses.add(spokenText);
            adapter.notifyDataSetChanged();
        }
    }
}
