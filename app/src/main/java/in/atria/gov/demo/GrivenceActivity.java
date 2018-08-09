package in.atria.gov.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import in.atria.gov.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GrivenceActivity extends AppCompatActivity {

    private int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grivence);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
        Button button = findViewById(R.id.o);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }

    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }



    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent); onStartNewActivity();

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
