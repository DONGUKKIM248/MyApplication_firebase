package com.example.myapplication_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText1,editText2;
    private Button button;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); //Firebase 연결
    DatabaseReference conditionRef1 = mRootRef.child("User").child("userID");
    DatabaseReference conditionRef2 = mRootRef.child("User").child("userPw");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview1);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button1);
    }
    protected void onStart() {
        super.onStart();

        conditionRef1.addListenerForSingleValueEvent(new ValueEventListener() {//값이 추가되는걸 Listen하는 함수
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //값이 변경되면
                String text = snapshot.getValue(String.class);
                textView.setText(text);//text 값으로 set을 해주고, textView에 찍어준다.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() { //button이 클릭되었을 때
            public void onClick(View v) {
                conditionRef1.setValue(editText1.getText().toString()); //conditionRef값을 editText에서 Text얻어서 String으로 변환해서 갱신한다.
                conditionRef2.setValue(editText2.getText().toString());
            }
        });
    }
}