package com.meetup.nativemobiledevbh;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


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

    @ViewById(R.id.imageAvatar)
    ImageView imageAvatar;
    @Extra
    User user;

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

        if(user != null) {
            setTitle(user.getName());

            name.setText(user.getName());
            email.setText(user.getEmail());
            login.setText(user.getLogin());

            if(user.getAvatarUrl() != null){
                beginGetAvatar();
            }
        }
    }

    @Background
    void beginGetAvatar(){
        URL newurl = null;
        try {
            newurl = new URL(user.getAvatarUrl());
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            endGetAvatar(null, mIcon_val);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @UiThread
    void endGetAvatar(Exception e, Bitmap b){
        imageAvatar.setImageBitmap(b);
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

                if(user.getEmail() != null){
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("application/image");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Native - Meetup funciona demais");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Native");
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(selectedImage.toString()));
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }

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
