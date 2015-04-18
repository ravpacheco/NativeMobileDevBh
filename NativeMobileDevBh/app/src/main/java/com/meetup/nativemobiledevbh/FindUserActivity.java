package com.meetup.nativemobiledevbh;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class FindUserActivity extends ActionBarActivity {

    @ViewById(R.id.userName)
    EditText userNameEditText;

    @ViewById(R.id.findButton)
    Button findUserButton;


    @AfterViews
    void init(){

    }

    @Click(R.id.findButton)
    void findUser(){

    }
}
