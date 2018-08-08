package in.atria.gov.demo.Intro;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by s
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        Intent intent = new Intent(SplashActivity.this, WelcomeAct.class);

        SplashActivity.this.startActivity(intent);
        finish();


    }
}