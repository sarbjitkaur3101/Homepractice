package com.sarb.homepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {
    EditText edt_enteremail;
    TextView txt_LoginForgetpass;
    Button btn_resetpass;
    String uemail;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        edt_enteremail = findViewById(R.id.edt_ForgetpassEmail);
        txt_LoginForgetpass = findViewById(R.id.txt_login);
        btn_resetpass = findViewById(R.id.btn_Recoverpass);
        auth = FirebaseAuth.getInstance();

        txt_LoginForgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(a);
            }
        });
        btn_resetpass.setOnClickListener(Reset);

    }

    View.OnClickListener Reset = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            uemail = edt_enteremail.getText().toString().trim();
            if (TextUtils.isEmpty(uemail)) {
                Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_LONG).show();
                return;
            }
            auth.sendPasswordResetEmail(uemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(ForgetActivity.this, "Lnk sent successful", Toast.LENGTH_SHORT).show();
                    Intent d = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(d);
                }


            });
        }
    };

}





