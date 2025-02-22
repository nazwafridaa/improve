package astratech.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import astratech.myapplication.R;
import astratech.myapplication.ui.fragment.HomeFragment;
import astratech.myapplication.ui.fragment.LeaderBoardFragment;
import astratech.myapplication.ui.fragment.ProfileFragment;
import astratech.myapplication.ui.fragment.ReviewFragment;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    FloatingActionButton btnAdd;
    ProfileFragment firstFragment = new ProfileFragment();
    HomeFragment mHomeFragment = new HomeFragment();
    LeaderBoardFragment thirdFragment = new LeaderBoardFragment();
    ReviewFragment reviewFragment = new ReviewFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        btnAdd = findViewById(R.id.fab);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, mHomeFragment).commit();
                return true;
            case R.id.review:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, reviewFragment).commit();
                return true;
            case R.id.leaderboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
                return true;
        }
        return false;
    }


}