package com.sarb.homepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(menu);
    }

    BottomNavigationView.OnNavigationItemSelectedListener menu=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){

                case R.id.home_menu:
                    navController=Navigation.findNavController(HomeActivity.this,R.id.home_host_nav);
                    navController.navigate(R.id.homeFragment);
                     break;
                case R.id.cart_menu:
                    navController=Navigation.findNavController(HomeActivity.this,R.id.home_host_nav);
                    navController.navigate(R.id.cartFragment);
                                 break;

                case R.id.profile_menu:
                    navController=Navigation.findNavController(HomeActivity.this,R.id.home_host_nav);
                    navController.navigate(R.id.profileFragment);

                                 break;




            }



            return true;
        }
    };



}