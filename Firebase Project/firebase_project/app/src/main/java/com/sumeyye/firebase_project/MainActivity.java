package com.sumeyye.firebase_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView yaz;
    EditText ad,soyad;
    Button degergonder,degergoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final  DatabaseReference myRef =database.getReference("message");

        yaz =findViewById(R.id.ekranayaz) ;

        ad = findViewById(R.id.edt_name);
        soyad = findViewById(R.id.edt_surname);

        degergonder = findViewById(R.id.btn_gonder);
        degergoster = findViewById(R.id.btn_goster);

        degergonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String degeral = ad.getText().toString().toUpperCase()+ " " +soyad.getText().toString().toUpperCase();
                myRef.setValue(degeral);

                Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                    }
                });

            }
        });


        degergoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue(String.class);
                        yaz.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                    }
                });


            }
        });
    }


}