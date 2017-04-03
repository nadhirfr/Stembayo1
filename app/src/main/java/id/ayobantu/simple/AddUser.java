package id.ayobantu.simple;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ayobantu.simple.Helper.DatabaseHelper;
import id.ayobantu.simple.Model.User;

public class AddUser extends AppCompatActivity {

    EditText etAddUserUsername, etAddUserPassword;
    Button btAddUser;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etAddUserUsername = (EditText) findViewById(R.id.etAddUserUsername);
        etAddUserPassword = (EditText) findViewById(R.id.etAddUserPassword);
        btAddUser = (Button) findViewById(R.id.btnAddUser);
        db = new DatabaseHelper(this);

        btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etAddUserUsername.getText().toString().equals("") &&
                        !etAddUserPassword.getText().toString().equals("")) {

                    db.addUser(new User(etAddUserUsername.getText().toString(), etAddUserPassword.getText().toString()));
                } else {
                    Toast.makeText(v.getContext(), "Username atau password kosong", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(AddUser.this, DaftarUser.class);
                startActivity(intent);

            }
        });

    }
}
