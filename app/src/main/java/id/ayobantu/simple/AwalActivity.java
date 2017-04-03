package id.ayobantu.simple;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ayobantu.simple.Helper.DatabaseHelper;
import id.ayobantu.simple.Model.User;

public class AwalActivity extends AppCompatActivity {

    Button btnToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        btnToLogin = (Button) findViewById(R.id.btnToLogin);


        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ini buat ngisi pas pertama aja
//                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
//                db.addUser(new User("john","pass"));
//                db.addUser(new User("smith", "pass"));

                Intent i = new Intent(AwalActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });



    }
}
