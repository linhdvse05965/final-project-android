package fu.prm391.sxample.android_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText edPhone, edPass , edName;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getDataById();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String,Object> createAccount =  new HashMap<>();
                createAccount.put("phone",edPhone.getText().toString());
                createAccount.put("name",edName.getText().toString());
                createAccount.put("pass",edPass.getText().toString());
                db.collection("User").add(createAccount).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    finish();
                    }
                });
            }
        });
    }

    private void getDataById() {
        edPhone = findViewById(R.id.edPhoneSignUp);
        edPass = findViewById(R.id.edPassSignUp);
        edName = findViewById(R.id.edNameSignUp);
        btnSignUp = findViewById(R.id.btnSignup);
    }
}
