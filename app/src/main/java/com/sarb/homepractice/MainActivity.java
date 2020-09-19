package com.sarb.homepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edt_name, edt_email, edt_pass, edt_cpass, edt_mobile;
    Button btn_register;
    TextView txt_alreadyreg;
    String uname, uemail, umobile, upass, ucpass;
    FirebaseAuth auth;
    FirebaseUser curuser;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_name = findViewById(R.id.edt_mainName);
        edt_email = findViewById(R.id.edt_mainEmail);
        edt_pass = findViewById(R.id.edt_mainPass);
        edt_cpass = findViewById(R.id.edt_mainCpass);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        btn_register = findViewById(R.id.btn_mainReg);
        edt_mobile = findViewById(R.id.edt_mainMob);
        txt_alreadyreg = findViewById(R.id.txt_mainAll);
        txt_alreadyreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(a);
            }
        });

        btn_register.setOnClickListener(register);

    }

    View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            uname = edt_name.getText().toString().trim();
            uemail = edt_email.getText().toString().trim();
            upass = edt_pass.getText().toString().trim();
            ucpass = edt_cpass.getText().toString().trim();
            umobile = edt_mobile.getText().toString().trim();
            if (TextUtils.isEmpty(uname)) {
                edt_name.requestFocus();
                edt_name.setError(" name is required");
                return;

            } else if (TextUtils.isEmpty(uemail)) {
                edt_email.requestFocus();
                edt_email.setError("please enter your email");
                return;
            } else if (TextUtils.isEmpty(upass)) {
                edt_pass.requestFocus();
                edt_pass.setError("enter password");
                return;
            } else if (upass.length() < 6) {
                edt_pass.getText().clear();
                edt_pass.requestFocus();
                edt_pass.setError("password must be 6 characters");
                return;
            } else if (TextUtils.isEmpty(ucpass)) {
                edt_cpass.requestFocus();
                edt_cpass.setError("cant be empty");
                return;
            } else if (!ucpass.equals(upass)) {
                edt_cpass.getText().clear();
                edt_cpass.requestFocus();
                edt_cpass.setError(" password not matched");
                return;
            }
            auth.createUserWithEmailAndPassword(uemail, upass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Savedata();
                                Intent d = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(d);


                            } else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    edt_email.getText().clear();
                                    edt_email.getText().clear();
                                    edt_cpass.getText().clear();
                                    edt_email.setError(" email does exist ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), " user not created", Toast.LENGTH_LONG).show();

                            }

                        }

                    });
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        curuser=auth.getCurrentUser();
        if (curuser!=null){
            Toast.makeText(this, "user already login ", Toast.LENGTH_LONG).show();
            Intent s= new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(s);
        }
    }

    private void Savedata() {
        Map<String, String> user = new HashMap<>();
        user.put("Email", uemail);
        user.put("Name", uname);
        user.put("Mobile no", umobile);
        curuser = auth.getCurrentUser();
        db.collection(" Users").document(curuser.getUid())
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });


    }
}




























