package id.ayobantu.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ayobantu.simple.Helper.DatabaseHelper;
import id.ayobantu.simple.Model.User;

public class EditUser extends AppCompatActivity {

    EditText etEditUserUsername, etEditUserPassword;
    Button btEditUser;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etEditUserUsername = (EditText) findViewById(R.id.etEditUserUsername);
        etEditUserPassword = (EditText) findViewById(R.id.etEditUserPassword);
        btEditUser = (Button) findViewById(R.id.btnEditUser);
        db = new DatabaseHelper(this);
        final Intent intent = getIntent();

        etEditUserUsername.setText(intent.getStringExtra("username"));
        etEditUserPassword.setText(intent.getStringExtra("password"));

        btEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etEditUserUsername.getText().toString().equals("") &&
                        !etEditUserPassword.getText().toString().equals("")) {
                    db.updateUser(new User(intent.getIntExtra("id", 0),
                            etEditUserUsername.getText().toString(),
                            etEditUserPassword.getText().toString()));
                    Intent intent1 = new Intent(EditUser.this, DaftarUser.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(v.getContext(), "Tidak boleh kosong", Toast.LENGTH_SHORT);
                }
            }
        });

    }
}
