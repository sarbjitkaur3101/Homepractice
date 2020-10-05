package com.sarb.homepractice;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {
    TextView txt_email;
    EditText edt_name,edt_mob;
    Button btn_update,btn_logout,btn_delete;
    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser curUser;





    public ProfileFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth= FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_email= view.findViewById(R.id.txt_profileEmail);
        edt_name= view.findViewById(R.id.edt_profileName);
        edt_mob=view.findViewById(R.id.edt_profileMobile);
        btn_update= view.findViewById(R.id.btn_profileUpdate);
        btn_delete= view.findViewById(R.id.btn_profileDelete);
        btn_logout= view.findViewById(R.id.btn_signoutProfile);
        loadData();
        btn_update.setOnClickListener(update);
        btn_logout.setOnClickListener(logout);
        btn_delete.setOnClickListener(delete);


    }

    private void loadData() {

        curUser=auth.getCurrentUser();

        db.collection("Users").document(curUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists())
                    {
                        System.out.println("Document Snapshot :"+doc.getData());

                        txt_email.setText(doc.get("Email").toString());

                        edt_mob.setText(doc.get("Mobile no").toString());
                    }
                    Log.d("Profile","Doc="+task.getResult()+"Exc="+task.getException());
                }


                else {
                    Log.d("Profile","Doc2="+task.getResult()+"Exc="+task.getException());
                }

            }
        });

    }



    View.OnClickListener delete= new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        }
    };

    View.OnClickListener logout= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            auth.signOut();
            Intent a = new Intent(getActivity().getApplicationContext(),LoginActivity.class);
            startActivity(a);

        }
    };

    View.OnClickListener update=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity().getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}