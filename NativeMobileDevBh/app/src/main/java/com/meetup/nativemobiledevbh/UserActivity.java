package com.meetup.nativemobiledevbh;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@EActivity
public class UserActivity extends ActionBarActivity {

    @ViewById(R.id.name)
    TextView name;

    @ViewById(R.id.email)
    TextView email;

    @ViewById(R.id.login)
    TextView login;


    @Extra
    User findedUser;

    @AfterViews
    void init(){

        if(findedUser != null) {

            name.setText(findedUser.getName());
            email.setText(findedUser.getEmail());
            login.setText(findedUser.getLogin());
        }
    }

}
