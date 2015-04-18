package com.meetup.nativemobiledevbh;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.meetup.nativemobiledevbh.client.GitHubClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity
public class FindUserActivity extends ActionBarActivity {

    @ViewById(R.id.userName)
    EditText userNameEditText;

    @ViewById(R.id.findButton)
    Button findUserButton;

    @RestService
    GitHubClient gitHubClient;


    @AfterViews
    void init(){

    }

    @Click(R.id.findButton)
    void findUser(){
        beginGetUser(userNameEditText.getText().toString());
    }

    @Background
    void beginGetUser(String userName){

        User user = null;
        try{
             user = gitHubClient.getAccount(userName);
        } catch(Exception e){
            endGetUser(null, e);
        }

        endGetUser(user, null);
    }

    void endGetUser(User user, Exception e){

        if(e == null){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }

        if(user != null){
            UserActivity_.intent(this).findedUser(user).start();
        }
    }
}
