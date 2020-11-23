package com.example.sqliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper databaseHelper;
    EditText studentName;
    EditText id_txt;
    EditText studentSurname;
    EditText studentMarks;
    Button insertButton;
    Button buttonShowData;
    Button updateButton;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        studentName = findViewById(R.id.student_name);
        studentSurname = findViewById(R.id.student_sName);
        studentMarks = findViewById(R.id.student_marks);
        insertButton = findViewById(R.id.button);
        updateButton = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);
        id_txt = findViewById(R.id.id_txt);
        buttonShowData = findViewById(R.id.button_retreive);
        insertButton.setOnClickListener(this);
        buttonShowData.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                boolean result = databaseHelper.inserData(studentName.getText().toString(),
                        studentSurname.getText().toString(),
                        studentMarks.getText().toString());
                if (result == true) {
                    Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(this, "Data can't be inserted", Toast.LENGTH_SHORT);
                }
                break;
            }
            case R.id.button_retreive: {
                Cursor cursor = databaseHelper.getDatabaseData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "NO data found!", Toast.LENGTH_SHORT);
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    stringBuffer.append("ID :" + cursor.getString(0) + "\n");
                    stringBuffer.append("Name :" + cursor.getString(1) + "\n");
                    stringBuffer.append("Sur Name: " + cursor.getString(2) + "\n");
                    stringBuffer.append("Marks :" + cursor.getString(3) + "\n\n");

                }
                showDataInDialog("Database Data", stringBuffer.toString());
            }
            case R.id.button_update: {
                boolean upData = databaseHelper.UpdateData(id_txt.getText().toString(),
                        studentName.getText().toString(),
                        studentSurname.getText().toString(),
                        studentMarks.getText().toString());
                if (upData == true) {
                    Toast.makeText(MainActivity.this, "Data updated successfully!", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(MainActivity.this, "Data can't updated", Toast.LENGTH_SHORT);
                }
            }
            case R.id.button_delete: {
                Integer deleteC = databaseHelper.delete(id_txt.getText().toString());
                if (deleteC < 0) {
                    Toast.makeText(MainActivity.this, "Data deleted Successfully!", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(MainActivity.this, "Data deleted Successfully!", Toast.LENGTH_SHORT);
                }

            }
        }
    }

    private void showDataInDialog(String database_data, String data) {
        AlertDialog.Builder altertDialog = new AlertDialog.Builder(this);
        altertDialog.setTitle(database_data);
        altertDialog.setCancelable(true);
        altertDialog.setMessage(data);
        altertDialog.show();
    }
}