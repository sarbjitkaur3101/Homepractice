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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.nio.file.FileAlreadyExistsException;

public class LoginActivity extends AppCompatActivity {
    EditText edt_email,edt_pass;
    TextView txt_forgetpass,txt_alreadyreg;
    Button btn_Login;
    String uemail,upass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        edt_email= findViewById(R.id.edt_LoginEmail);
        edt_pass= findViewById(R.id.edt_LoginPass);
        txt_forgetpass= findViewById(R.id.txt_Loginforgetpass);
        txt_alreadyreg= findViewById(R.id.txt_LoginNewuser);
        btn_Login= findViewById(R.id.btn_Login);
        txt_alreadyreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);

            }
        });
        txt_forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f= new Intent(getApplicationContext(),ForgetActivity.class);
                startActivity(f);
            }
        });
        btn_Login.setOnClickListener(Login);
    }


    View.OnClickListener Login= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            uemail= edt_email.getText().toString().trim();
            upass=edt_pass.getText().toString().trim();
            if (TextUtils.isEmpty(uemail)){
                edt_email.requestFocus();
                edt_email.setError("Email cant be empty");
                return;

            }
            else if(TextUtils.isEmpty(upass)){
                edt_pass.requestFocus();
                edt_pass.setError(" password required");
                return;

            }
            else if(upass.length()<6){
                edt_pass.getText().clear();
                edt_pass.requestFocus();
                edt_pass.setError(" password must be at least");
                return;

            }
            else {
                auth.signInWithEmailAndPassword(uemail,upass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "user loged in", Toast.LENGTH_LONG).show();
                            Intent d = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(d);


                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                edt_email.getText().clear();
                                edt_pass.getText().clear();
                                edt_email.setError("user doesn't exist");
                                edt_email.requestFocus();
                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                        }


                    }


        });
    }


}
    };
}
