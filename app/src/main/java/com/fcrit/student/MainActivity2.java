package com.fcrit.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    EditText name,roll_no;
    Button submitButton,queryButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Hooks to xml elements
        name = findViewById(R.id.name);
        roll_no = findViewById(R.id.roll_no);

        submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String names = name.getText().toString();
                String rollNos = roll_no.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(names,rollNos);
                reference.child(rollNos).setValue(helperClass);
            }
        });

        queryButton = findViewById(R.id.query_button);
        queryButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(intent);
        });

    }
}