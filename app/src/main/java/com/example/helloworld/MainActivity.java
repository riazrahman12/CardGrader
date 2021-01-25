package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
//        import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    // Define the pic id
    private static final int pic_id = 123;
    private static final int pic_id2 = 124;
    // Define the button and imageview type variable
    Button camera_open_id;
    ImageView click_image_id;

    TextView status_id, ratio_id;
    TextView grading_direction_id, horizontal_ratio_id, vertical_ratio_id, furry_id;
//    private Bitmap bitmap;

    // Defining Permission codes.
    // We can give any value
    // but unique for each permission.
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_READ_PERMISSION_CODE = 101;
    private static final int STORAGE_WRITE_PERMISSION_CODE = 102;

    Uri imageUri_1;
    Uri imageUri_2;

    int[] pixels_1, pixels_2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // By ID we can get each component
        // which id is assigned in XML file
        // get Buttons and imageview.
        camera_open_id = (Button)findViewById(R.id.camera_button);
        click_image_id = (ImageView)findViewById(R.id.click_image);

        status_id = (TextView) findViewById(R.id.status);
        ratio_id = (TextView) findViewById(R.id.ratio);
//        ratio_exact_id = (TextView) findViewById(R.id.ratio_exact);
//        left_id = (TextView) findViewById(R.id.left);
//        right_id = (TextView) findViewById(R.id.right);
//        top_id= (TextView) findViewById(R.id.top);
//        bottom_id = (TextView) findViewById(R.id.bottom);

        grading_direction_id = (TextView) findViewById(R.id.grading_direction);
        horizontal_ratio_id = (TextView) findViewById(R.id.horizontal_ratio);
        vertical_ratio_id = (TextView) findViewById(R.id.vertical_ratio);

        furry_id = (TextView) findViewById(R.id.furry);

        // Camera_open button is for open the camera
        // and add the setOnClickListener in this button
        camera_open_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if(!checkPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_READ_PERMISSION_CODE)){

                    Toast.makeText(MainActivity.this,
                            "STORAGE PERMISSION REQUIRED",
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

                if(!checkPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        STORAGE_WRITE_PERMISSION_CODE)){

                    Toast.makeText(MainActivity.this,
                            "STORAGE PERMISSION REQUIRED",
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

                if(!checkPermission(
                        Manifest.permission.CAMERA,
                        CAMERA_PERMISSION_CODE)){

                    Toast.makeText(MainActivity.this,
                            "CAMERA PERMISSION REQUIRED",
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }


                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri_1 = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri_1);
                startActivityForResult(intent, pic_id);

//                ContentValues values_2 = new ContentValues();
//                values_2.put(MediaStore.Images.Media.TITLE, "New Picture 2");
//                values_2.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera 2");
//                imageUri_2 = getContentResolver().insert(
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values_2);
//                Intent intent_2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent_2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri_2);
//                startActivityForResult(intent_2, pic_id2);



//                // Create the camera_intent ACTION_IMAGE_CAPTURE
//                // it will open the camera for capture the image
//                Intent camera_intent
//                        = new Intent(MediaStore
//                        .ACTION_IMAGE_CAPTURE);
//
//                // Start the activity with camera_intent,
//                // and request pic id
//                startActivityForResult(camera_intent, pic_id);
            }
        });
    }

    // Function to check and request permission.
    public boolean checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { permission },
                    requestCode);
            if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                    == PackageManager.PERMISSION_DENIED) {
                return false;
            }
            else{
                return true;
            }
        }
        else {
//            Toast.makeText(MainActivity.this,
//                    "Permission already granted",
//                    Toast.LENGTH_SHORT)
//                    .show();
            return true;
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(MainActivity.this,
//                        "Camera Permission Granted",
//                        Toast.LENGTH_SHORT)
//                        .show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == STORAGE_READ_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(MainActivity.this,
//                        "Storage Permission Granted",
//                        Toast.LENGTH_SHORT)
//                        .show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == STORAGE_WRITE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(MainActivity.this,
//                        "Storage Permission Granted",
//                        Toast.LENGTH_SHORT)
//                        .show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }



    // This method will help to retrieve the image
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        // Match the request 'pic id with requestCode
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
//            Bitmap photo = (Bitmap) data.getExtras()
//                    .get("data");

//            Bitmap photo = BitmapFactory.decodeResource(getResources(),R.mipmap.custom_rotated);

            int w, h;

            Bitmap original_photo = null;
            Bitmap photo = null;
            Bitmap rotated_photo = null;
            try {
                original_photo = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri_1);
//                original_photo = BitmapFactory.decodeResource(getResources(),R.mipmap.samsung);

                int old_w = original_photo.getWidth();
                int old_h = original_photo.getHeight();
                if (old_w > old_h) {
//                    int temp = old_w;
//                    old_w = old_h;
//                    old_h = temp;

                    int resized_width = 2080;
                    double factor = (double) original_photo.getWidth() / (double) resized_width;
                    int resized_height = (int) ((double) original_photo.getHeight() / factor); //2080 for redmi

                    photo = Bitmap.createScaledBitmap(
                            original_photo, resized_width, resized_height, false);


                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    rotated_photo = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
//                    original_photo = rotated_photo;
                    photo = rotated_photo;
                } else {

                    int resized_width = 1560;
                    double factor = (double) original_photo.getWidth() / (double) resized_width;
                    int resized_height = (int) ((double) original_photo.getHeight() / factor); //2080 for redmi

//                photo = Bitmap.createScaledBitmap(
//                        original_photo, 1560, 2080, false);
                    photo = Bitmap.createScaledBitmap(
                            original_photo, resized_width, resized_height, false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            w = photo.getWidth();
            h = photo.getHeight();

//                int[] piexls = new int[w * h];
            pixels_1 = new int[w * h];
            photo.getPixels(pixels_1, 0, w, 0, 0, w, h);


            Toast.makeText(MainActivity.this,
                    "Take Second Image",
                    Toast.LENGTH_SHORT);

            ContentValues values_2 = new ContentValues();
            values_2.put(MediaStore.Images.Media.TITLE, "New Picture 2");
            values_2.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera 2");
            imageUri_2 = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values_2);
            Intent intent_2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent_2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri_2);
            startActivityForResult(intent_2, pic_id2);

        }

        else if(requestCode == pic_id2) {


            int w, h;

            Bitmap original_photo = null;
            Bitmap photo = null;
            Bitmap rotated_photo = null;
            try {
                original_photo = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri_2);
//                original_photo = BitmapFactory.decodeResource(getResources(),R.mipmap.samsung);

                int old_w = original_photo.getWidth();
                int old_h = original_photo.getHeight();
                if (old_w > old_h) {
//                    int temp = old_w;
//                    old_w = old_h;
//                    old_h = temp;

                    int resized_width = 2080;
                    double factor = (double) original_photo.getWidth() / (double) resized_width;
                    int resized_height = (int) ((double) original_photo.getHeight() / factor); //2080 for redmi

                    photo = Bitmap.createScaledBitmap(
                            original_photo, resized_width, resized_height, false);


                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    rotated_photo = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
//                    original_photo = rotated_photo;
                    photo = rotated_photo;
                } else {

                    int resized_width = 1560;
                    double factor = (double) original_photo.getWidth() / (double) resized_width;
                    int resized_height = (int) ((double) original_photo.getHeight() / factor); //2080 for redmi

//                photo = Bitmap.createScaledBitmap(
//                        original_photo, 1560, 2080, false);
                    photo = Bitmap.createScaledBitmap(
                            original_photo, resized_width, resized_height, false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            w = photo.getWidth();
            h = photo.getHeight();

//                int[] piexls = new int[w * h];
            pixels_2 = new int[w * h];
            photo.getPixels(pixels_2, 0, w, 0, 0, w, h);


            if((pixels_1 == null) || (pixels_2 == null)){
                return;
            }

//            int[] resultData = com.example.helloworld.NativeLib.Bitmap2Grey(piexls,w,h);
            OutputStruct resultData = NativeLib.Bitmap2Grey(pixels_1, pixels_2, w, h);


//            Context context = getApplicationContext();
////            CharSequence text = "Hello toast!";
//            String text = String.valueOf(w)+" "+String.valueOf(h);
//
////            double left_x, right_x, top_y, bottom_y;
////            double x_percentage, y_percentage;
////            double x_percentage_exact, y_percentage_exact;
////            boolean status;
//
//
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();


            if (resultData.status != 0) {

                if (resultData.status == 1) {
                    status_id.setText("Not Graded");
                } else {
                    status_id.setText("Graded");
                }


                Bitmap resultImage = Bitmap.createBitmap(resultData.output_width, resultData.output_height, Bitmap.Config.ARGB_8888);
                resultImage.setPixels(resultData.output_image, 0, resultData.output_width, 0, 0, resultData.output_width, resultData.output_height);

                // Set the image in imageview for display
                click_image_id.setImageBitmap(resultImage);


                ratio_id.setText(String.valueOf(resultData.x_percentage) + " : " + String.valueOf(resultData.y_percentage));


                resultData.left_percentage = (double) Math.round(resultData.left_percentage * 100) / 100;
                resultData.right_percentage = (double) Math.round(resultData.right_percentage * 100) / 100;
                resultData.top_percentage = (double) Math.round(resultData.top_percentage * 100) / 100;
                resultData.bottom_percentage = (double) Math.round(resultData.bottom_percentage * 100) / 100;


                if (resultData.status == 1) {
                    grading_direction_id.setText("NA");
                } else if (resultData.status == 2) {
                    grading_direction_id.setText("Horizontal");
                } else if (resultData.status == 3) {
                    grading_direction_id.setText("Vertical");
                }


                horizontal_ratio_id.setText(String.valueOf(resultData.left_percentage) + " : " + String.valueOf(resultData.right_percentage));
                vertical_ratio_id.setText(String.valueOf(resultData.top_percentage) + " : " + String.valueOf(resultData.bottom_percentage));

            } else if (resultData.status == 0) {
                status_id.setText("Not Detected");
                Bitmap resultImage = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                resultImage.setPixels(resultData.image_data, 0, w, 0, 0, w, h);

                // Set the image in imageview for display
                click_image_id.setImageBitmap(resultImage);
                ratio_id.setText(String.valueOf(0) + " : " + String.valueOf(0));
                grading_direction_id.setText("NA");
                horizontal_ratio_id.setText(String.valueOf(0) + " : " + String.valueOf(0));
                vertical_ratio_id.setText(String.valueOf(0) + " : " + String.valueOf(0));
            }

            if(resultData.furry){
                furry_id.setText("Pass");
            }
            else{
                furry_id.setText("Fail");
            }

//            ratio_exact_id.setText(String.valueOf(resultData.x_percentage_exact)+" : "+String.valueOf(resultData.y_percentage_exact));
//
//            left_id.setText(String.valueOf(resultData.left_x));
//            right_id.setText(String.valueOf(resultData.right_x));
//            top_id.setText(String.valueOf(resultData.top_y));
//            bottom_id.setText(String.valueOf(resultData.bottom_y));
        }

    }

}