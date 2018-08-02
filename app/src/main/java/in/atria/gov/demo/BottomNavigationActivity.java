package in.atria.gov.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import in.atria.gov.demo.Fragments.HomeFragment;
import in.atria.gov.demo.Fragments.ProfileFragment;

import android.view.MenuItem;
import android.widget.TextView;

public class BottomNavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


        ActionBar actionBar;
        actionBar= getSupportActionBar();
        actionBar.hide();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
        navigation.getMenu().getItem(0).setChecked(true);


    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            HomeFragment frg1 = new HomeFragment ();

            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    openFragment(frg1);
                    break;

                case R.id.navigation_contact:
                    startActivity(new Intent(getApplicationContext(),ContactActivity.class));
                    break;

                case R.id.navigation_report:
                    startActivity(new Intent(getApplicationContext(),ReportActivity.class));
                    break;

                case R.id.navigation_profile:
                    ProfileFragment frg12 = new ProfileFragment ();
                    openFragment(frg12);
                    break;


                default:
                    openFragment(frg1);
                    break;



            }

            return true;
        }
    };
}
