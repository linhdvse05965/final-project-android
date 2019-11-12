package fu.prm391.sxample.android_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    EditText edPhone, edPass;
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getDataById();
    }

    private void getDataById() {
        edPhone = findViewById(R.id.edPhone);
        edPass = findViewById(R.id.edPass);
        btnSignin = findViewById(R.id.btnSignIn);
    }
}
