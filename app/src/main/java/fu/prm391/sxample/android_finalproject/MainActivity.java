package fu.prm391.sxample.android_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView txtSlogan;
Button btnSignInMain,btnSignUpMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataById();
        btnSignUpMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnSignInMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , SignInAcitivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDataById(){
        txtSlogan = findViewById(R.id.txtSlogan);
        btnSignInMain = findViewById(R.id.btnSignInMain);
        btnSignUpMain = findViewById(R.id.btnSignUpMain);
    }
}
