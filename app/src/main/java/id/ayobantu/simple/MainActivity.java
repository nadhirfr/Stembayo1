package id.ayobantu.simple;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.ayobantu.simple.Helper.DatabaseHelper;
import id.ayobantu.simple.Model.User;

public class MainActivity extends AppCompatActivity {

    TextView tvUsername;
    Button btLihatUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        btLihatUser = (Button) findViewById(R.id.btLihatUser);

        btLihatUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DaftarUser.class);
                startActivity(intent);
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Intent i = getIntent();
        int loggedinID = databaseHelper.findUser(i.getStringExtra("user"),
                i.getStringExtra("pass"));
        if (loggedinID != 0) {
            User user = databaseHelper.getUser(loggedinID);
            tvUsername.setText("Hello, " + user.getUsername());
            btLihatUser.setVisibility(View.VISIBLE);
        } else {
            tvUsername.setText("Maaf username atau password salah :(");
            btLihatUser.setVisibility(View.INVISIBLE);
        }


    }
}
