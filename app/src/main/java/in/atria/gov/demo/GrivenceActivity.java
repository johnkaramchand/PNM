package in.atria.gov.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import in.atria.gov.demo.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GrivenceActivity extends AppCompatActivity {

    TextView  locationdata;
    int PLACE_PICKER_REQUEST = 123;
    private int REQUEST_CODE=1;
    private int GALLERY = 1, CAMERA = 2;
    private static final String IMAGE_DIRECTORY = "/mws";

    private void showPictureDialog(final int from){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary(from);
                                break;
                            case 1:
                                takePhotoFromCamera(from);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary(int from) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(galleryIntent, GALLERY+from);
    }
    private void takePhotoFromCamera(int from) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA+from);
    }
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grivence);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        locationdata = findViewById(R.id.locationdata);

        Button button = findViewById(R.id.o);

        LinearLayout location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                //Intent intent = new Intent();
                //   intent.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);
                try {

                    startActivityForResult(builder.build(GrivenceActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });




        LinearLayout selecttaluk,selectpanchayat;
        selecttaluk = findViewById(R.id.selecttaluk);
        selectpanchayat = findViewById(R.id.selectpanchayat);


        selecttaluk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GrivenceActivity.this,SelectTalukActivity.class);
                startActivityForResult(i,0000);

            }
        });
        selectpanchayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GrivenceActivity.this,PanchayatActivity.class);
                startActivityForResult(i,1111);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> report = new HashMap<>();
                report.put("lat", "10");
                report.put("lon", "11");
                report.put("taluk", "chikballapur");
                report.put("village", "village name");
                report.put("problem", "description");
                report.put("status", "Submitted");
                report.put("Reported to", "Mr surya");
                report.put("number", "99016538822");
                report.put("user id", "1234");
                report.put("user name", "Mr abc");
                report.put("user number", "99072727277");
                // Add a new document with a generated ID
                db.collection("users").document("9901624795").collection("reports")
                        .add(report)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "Success!",
                                        Toast.LENGTH_LONG).show();
                                }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error!",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                }
        });
          Button image = findViewById(R.id.button);
          image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog(REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /////////////////////////////////////////////////////
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode== 0000){
            if (null!=data){
                String message = data.getStringExtra("selectedtaluk");
                Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode== 1111){
            if (null!=data){
                String message = data.getStringExtra("selectedpanchayat");
                Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                Place selectedPlace = PlacePicker.getPlace(data, this);
                // Do something with the place
                Toast.makeText(this, ""+selectedPlace.getAddress(), Toast.LENGTH_SHORT).show();
              //  address.setText(selectedPlace.getAddress());
                locationdata.setText(selectedPlace.getAddress());



            }
        }
        if (requestCode == GALLERY+REQUEST_CODE) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(GrivenceActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();

                    //Firebase
                    FirebaseStorage storage;
                    StorageReference storageReference;
                    storage = FirebaseStorage.getInstance();
                    storageReference = storage.getReference();
                    StorageReference ref = storageReference.child("/Users/apple/StudioProjects/PNM/app/src/main/res/drawable/mountains.jpeg");
                    ref.putFile(contentURI)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                            .getTotalByteCount());
                                }
                            });
                        /*voterb.setImageBitmap(bitmap);
                        voterb.setScaleType(ImageView.ScaleType.CENTER);
                        */
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(GrivenceActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
