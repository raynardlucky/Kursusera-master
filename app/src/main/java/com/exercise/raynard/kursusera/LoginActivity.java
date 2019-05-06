package com.exercise.raynard.kursusera;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText passwordText;
    private Button loginBtn;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
//            intent = new Intent(this, BottomNavigationActivity.class);
            startActivity(new Intent(this, BottomNavigationActivity.class));
        }

        emailText = (EditText) findViewById(R.id.email_edit_text);
        passwordText = (EditText) findViewById(R.id.password_edit_text);
        loginBtn = (Button) findViewById(R.id.login_button);

        progressDialog = new ProgressDialog(this);
    }

    public void toBottomNav(View view) {
        final Intent intent = new Intent(this, BottomNavigationActivity.class);
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Signing In Please Wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            progressDialog.dismiss();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Username or password incorrect",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }
}