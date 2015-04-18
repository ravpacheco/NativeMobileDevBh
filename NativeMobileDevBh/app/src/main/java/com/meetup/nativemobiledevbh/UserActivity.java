package com.meetup.nativemobiledevbh;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.io.File;


@EActivity(R.layout.activity_user)
public class UserActivity extends ActionBarActivity {

    @ViewById(R.id.name)
    TextView name;

    @ViewById(R.id.email)
    TextView email;

    @ViewById(R.id.login)
    TextView login;

    @ViewById(R.id.imageGitHub)
    ImageView imageView;

    @Extra
    User findedUser;

    protected final static int TAKE_PICTURE = 1;
    private Uri imageUri;

    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @AfterViews
    void init(){

        if(findedUser != null) {

            name.setText(findedUser.getName());
            email.setText(findedUser.getEmail());
            login.setText(findedUser.getLogin());
        }
    }

    @Click(R.id.openCamera)
    void openCamera(){
        takePhoto();
    }


    @OnActivityResult(TAKE_PICTURE)
    void result(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            Uri selectedImage = imageUri;
            getContentResolver().notifyChange(selectedImage, null);
            ContentResolver cr = getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = android.provider.MediaStore.Images.Media
                        .getBitmap(cr, selectedImage);

                imageView.setImageBitmap(bitmap);
                Toast.makeText(this, selectedImage.toString(),
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                        .show();
                Log.e("Camera", e.toString());
            }
        }
    }

}
