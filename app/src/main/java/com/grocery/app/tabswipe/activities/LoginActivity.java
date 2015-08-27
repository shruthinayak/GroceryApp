package com.grocery.app.tabswipe.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grocery.app.tabswipe.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends ActionBarActivity {
    EditText mUsername;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mUsername = (EditText) findViewById(R.id.edtemail);
        mPassword = (EditText) findViewById(R.id.edtpassword);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Button btnSignUp = (Button) findViewById(R.id.btn_signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = mUsername.getText().toString().trim().toLowerCase();
                String password = mPassword.getText().toString().trim();

                if (emailId.equals("") || password.equals(""))
                    Toast.makeText(getApplicationContext(), R.string.signup_form_incomplete, Toast.LENGTH_LONG).show();
                else {
                    ParseUser.logInInBackground(emailId, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (e != null) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                return;
                            }
                            if (parseUser == null) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = mUsername.getText().toString().trim().toLowerCase();
                String password = mPassword.getText().toString().trim();

                if (emailId.equals("") || password.equals(""))
                    Toast.makeText(getApplicationContext(), R.string.signup_form_incomplete, Toast.LENGTH_LONG).show();
                else {
                    ParseUser user = new ParseUser();
                    user.setUsername(emailId);
                    user.setEmail(emailId);
                    user.setPassword(password);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

}
