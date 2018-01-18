package com.example.shoham.loginscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class informationActivity extends AppCompatActivity {


    private static final String REQUIRED = "Required";
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int CAMERA_REQUEST = 1;
    private ImageView imgPicture;
    private FirebaseAuth mAuth;
    public Button butl;
    public Button updateProfilebtn;
    private Uri pictureUri;
    private DatabaseReference mDatabase;
    private String firstName,lastName,address,city,email,phone;
    private EditText firstNameField,lastNameField,addressField,cityField,emailField,phoneField;
    private int randId;
    private String manegerPass="7777777";

    // FIREBASE STORAGE REF
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference userProfileRef;
    StorageReference storageRef;

    //INIT IO STREAM
    ByteArrayOutputStream baos;
    String userId ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);



        ////ASSIGN ID
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        updateProfilebtn = (Button) findViewById(R.id.bUpdateProfile);

        // init the profile filed
        firstNameField = (EditText) findViewById(R.id.etFirstName);
        lastNameField = (EditText) findViewById(R.id.etLastName);
        addressField = (EditText) findViewById(R.id.etAddress);
        cityField= (EditText) findViewById(R.id.etCity);
        emailField = (EditText) findViewById(R.id.etEmail);
        phoneField = (EditText) findViewById(R.id.etPhone);

        // init the database
        mDatabase = FirebaseDatabase.getInstance().getReference();


        /// update the profile
        updateProfilebtn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick (View view){

                       firstName = firstNameField.getText().toString().trim();
                       lastName = lastNameField.getText().toString().trim();
                       address = addressField.getText().toString().trim();
                       city = cityField.getText().toString().trim();
                       email = emailField.getText().toString().trim();
                       phone = phoneField.getText().toString().trim();

                   if (!checkFieldBeforeCreateUser(firstName,lastName,address,city,email,phone)){
                       Toast.makeText(informationActivity.this,"please fill the missing field", Toast.LENGTH_LONG).show();
                   }
                   else {
                       userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                       Toast.makeText(informationActivity.this, userId, Toast.LENGTH_LONG).show();
                       writeNewUser(userId);
                   }
            }

        });



    }


    /**
     *     upload a picture file and save him on firebase storage
      */

public void upploadFromImgView(){

    //init the firebase storge to store photos and video
    storageRef = storage.getReference();
    userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    System.out.println(userId);
    // Points to "images"
   // String pictureName = userId.toString();
    userProfileRef = storageRef.child("userProfile/"+userId+".jpg");

    imgPicture.setDrawingCacheEnabled(true);
    imgPicture.buildDrawingCache();
    Bitmap bitmap = imgPicture.getDrawingCache();
    baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] data = baos.toByteArray();

    UploadTask uploadTask = userProfileRef.putBytes(data);
    uploadTask.addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {
            // Handle unsuccessful uploads
            Toast.makeText(informationActivity.this,"Error uploading picture ", Toast.LENGTH_LONG).show();
        }
    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
            Uri downloadUrl = taskSnapshot.getDownloadUrl();
            Toast.makeText(informationActivity.this,downloadUrl.toString(), Toast.LENGTH_LONG).show();
        }
    });

}



    /**
     * This method will be invoked when the user clicks on gallery button
     *
     * @param v
     */
    public void onImageGallClicked(View v) {
        Toast.makeText(this, "Gallery MODE , PLEASE Choose PHOTO", Toast.LENGTH_LONG).show();

        // invoke the image gallery using an implict intent.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        // set the data and type.  get only image ,get all image types.
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }


    /** create User by field
     *
     * @param userId
     */
    public void writeNewUser(String userId) {
        User user = new User(firstName,lastName,address, city,email,phone);

        mDatabase.child("users").child(userId).setValue(user);
/*
        if( manegerPass=="123456"){
            mDatabase.child("Manegers").child(userId).setValue(new Maneger(user,manegerPass));
        }
*/

    }


    /**
     * This method will be called when the Take Photo button is clicked.
     * open the camera mode .
     *
     * @param
     */

    public void btnTakePhoto(View v) {
        System.out.println("in btn ");
        Toast.makeText(this, "CAMERA MODE , PLEASE TAKE PHOTO", Toast.LENGTH_LONG).show();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    /**
     * This method will be called when the Take Photo button is clicked and create a file to be saved.
     *sometimes throws exception should use fileprovider
     * @param v
     */
    public void btnTakePhotoClicked(View v) {
        System.out.println("in clicked ");
        Toast.makeText(this, "CAMERA MODE , PLEASE TAKE PHOTO", Toast.LENGTH_LONG).show();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureName = getPictureName();
        File imageFile = new File(pictureDirectory, pictureName);
        pictureUri = Uri.fromFile(imageFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "Photo" + timestamp + ".jpg";

    }


    /**
     * check the submition field of the user
     * @param firstN first name
     * @param LastN last name
     * @param address adress
     * @param city city filed
     * @param email email filed
     * @param phone phone number field
     * @return true if all the nesscery file full or false else .
     */
    private boolean checkFieldBeforeCreateUser(String firstN , String LastN ,String address , String city ,String email ,String phone ) {

        // firstName is required
        if (TextUtils.isEmpty(firstN)) {
            firstNameField.setError(REQUIRED);
            return false;
        }

        // LastName is required
        if (TextUtils.isEmpty(LastN)) {
            lastNameField.setError(REQUIRED);
            return false;
        }
        // addressField is required
      //  if (TextUtils.isEmpty(address)) {
        //    addressField.setError(REQUIRED);
          //  return false;
       // }

        // city is required
       // if (TextUtils.isEmpty(city)) {
         //   cityField.setError(REQUIRED);
           // return false;

        //}
        // emailField is required
        if (TextUtils.isEmpty(email)) {
            emailField.setError(REQUIRED);
            return false;
        }
        // phoneField is required
        if (TextUtils.isEmpty(phone)) {
            phoneField.setError(REQUIRED);
            return false;
        }
        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();
    return true;
    }


    /**
     * private func that respond to disapred the UPDATE  button
     * @param enabled
     */
    private void setEditingEnabled(boolean enabled) {

        firstNameField.setEnabled(enabled);
        lastNameField.setEnabled(enabled);
        addressField.setEnabled(enabled);
        cityField.setEnabled(enabled);
        phoneField.setEnabled(enabled);
        emailField.setEnabled(enabled);


        if (enabled) {
            updateProfilebtn.setVisibility(View.VISIBLE);
        } else {
            updateProfilebtn.setVisibility(View.GONE);
        }
    }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // if we are here, everything processed successfully.
                if (requestCode == CAMERA_REQUEST) {

                    // we are hearing back from the camera.
                    try{
                        Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                         imgPicture.setImageBitmap(cameraImage);
                        upploadFromImgView();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                      }
                    // at this point, we have the image from the camera.

                }
                 if (requestCode == IMAGE_GALLERY_REQUEST) {
                    // if we are here, we are hearing back from the image gallery.

                    // the address of the image on the SD Card.
                    Uri imageUri = data.getData();
                     String filename = imageUri.getLastPathSegment();
                   //  Toast.makeText(this, data.getDataString(), Toast.LENGTH_LONG).show();
                   //  Toast.makeText(this, imageUri.toString(), Toast.LENGTH_LONG).show();
                     // declare a stream to read the image data from the SD Card.

                    InputStream inputStream;

                    // we are getting an input stream, based on the URI of the image.
                    try {
                        inputStream = getContentResolver().openInputStream(imageUri);

                        // get a bitmap from the stream.
                        Bitmap image = BitmapFactory.decodeStream(inputStream);


                        // show the image to the user
                        imgPicture.setImageBitmap(image);
                        Toast.makeText(this, "SUCCESS LOADING THE PHOTO  ", Toast.LENGTH_LONG).show();
                        upploadFromImgView();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        // show a message to the user indictating that the image is unavailable.
                        Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }




