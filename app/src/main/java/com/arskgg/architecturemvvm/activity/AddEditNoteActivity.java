package com.arskgg.architecturemvvm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.arskgg.architecturemvvm.R;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.arskgg.architecturemvvm.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.arskgg.architecturemvvm.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.arskgg.architecturemvvm.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.arskgg.architecturemvvm.EXTRA_PRIORITY";

    private EditText titleEdt, descriptionEdt;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        titleEdt = findViewById(R.id.titleEdt);
        descriptionEdt = findViewById(R.id.descriptionEdt);
        numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMinValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");

            titleEdt.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_DESCRIPTION, 1));
        } else
            setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.saveNoteMenu:
                saveNote();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {

        String title = titleEdt.getText().toString();
        String description = descriptionEdt.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please fill the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
