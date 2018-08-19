package in.atria.gov.demo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelectTalukActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_taluk);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();





        CardView cardView = findViewById(R.id.chik);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMessage=new Intent();
                String message = "Chikkaballapura";
                intentMessage.putExtra("selectedtaluk",message);
                // Set The Result in Intent
                setResult(2,intentMessage);
// finish The activity
                finish();

            }
        });




    }
}
