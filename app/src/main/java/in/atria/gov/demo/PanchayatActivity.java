package in.atria.gov.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import in.atria.gov.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PanchayatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panchayat);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        CardView cardView = findViewById(R.id.yo);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanchayatActivity.this,GrivenceActivity.class));
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
}
