package com.example.cityguide.User;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguide.LocationOwner.OwnerDashboard;
import com.example.cityguide.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class OwnerAddNewActivity extends AppCompatActivity {

    private String CategoryName, Description, Address, Pname, saveCurrentDate, saveCurrentTime;
    private Button AddNewPlaceButton;
    private ImageView InputPlaceImage;
    private EditText InputPlaceName, InputPlaceDescription, InputPlaceAddress;
    private static final int GalleryPick =1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference PlaceImagesRef;
    private DatabaseReference PlaceRef;
    private DatabaseReference rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_new);

        CategoryName = getIntent().getExtras().get("category").toString();
        PlaceImagesRef = FirebaseStorage.getInstance().getReference().child("Place Images");
        PlaceRef = FirebaseDatabase.getInstance().getReference().child("Places");
        rest = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        //Toast.makeText(this, CategoryName, Toast.LENGTH_SHORT).show();
        AddNewPlaceButton = (Button) findViewById(R.id.add_new_place);
        InputPlaceImage = (ImageView) findViewById(R.id.select_place_image);
        InputPlaceName = (EditText) findViewById(R.id.place_name);
        InputPlaceDescription = (EditText) findViewById(R.id.place_description);
        InputPlaceAddress = (EditText) findViewById(R.id.place_address);

        InputPlaceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        AddNewPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatePlaceData();
            }
        });
    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            InputPlaceImage.setImageURI(ImageUri);
        }
    }

    private void ValidatePlaceData()
    {
        Description = InputPlaceDescription.getText().toString();
        Address = InputPlaceAddress.getText().toString();
        Pname = InputPlaceName.getText().toString();


        if(ImageUri == null)
        {
            Toast.makeText(this, "PLace image is mandatory!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Please enter the place description!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Address))
        {
            Toast.makeText(this, "Please enter the place address!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Please enter the place name!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StorePlaceInformation();
        }
    }

    private void StorePlaceInformation()
    {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = PlaceImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String  message = e.toString();
                Toast.makeText(OwnerAddNewActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(OwnerAddNewActivity.this, "Place Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(OwnerAddNewActivity.this, "got the  Place Image saved to database successfully!", Toast.LENGTH_SHORT).show();

                            SavePlaceInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SavePlaceInfoToDatabase()
    {
        HashMap<String, Object> placeMap = new HashMap<>();
        placeMap.put("pid", productRandomKey);
        placeMap.put("date", saveCurrentDate);
        placeMap.put("time", saveCurrentTime);
        placeMap.put("description", Description);
        placeMap.put("image", downloadImageUrl);
        placeMap.put("category", CategoryName);
        placeMap.put("address", Address);
        placeMap.put("pname", Pname);
        // check Here again
        if(CategoryName.equals("restaurants")) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("address", Address);
            map.put("description", Description);
            map.put("image", downloadImageUrl);
            map.put("restName", Pname);
            map.put("starsTillNow","0");
            map.put("Customercount","0");
            rest.child(productRandomKey).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                    }
                }
            });
        }



        PlaceRef.child(productRandomKey).updateChildren(placeMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(OwnerAddNewActivity.this, OwnerDashboard.class);
                            startActivity(intent);
                            Toast.makeText(OwnerAddNewActivity.this, "Place is added successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String message = task.getException().toString();
                            Toast.makeText(OwnerAddNewActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}