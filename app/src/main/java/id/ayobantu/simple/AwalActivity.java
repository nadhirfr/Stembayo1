package id.ayobantu.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

                Intent i = new Intent(AwalActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });



    }
}
