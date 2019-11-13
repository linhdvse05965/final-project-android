package fu.prm391.sxample.android_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText edPhone, edPass, edName;
    Button btnSignUp;
    FirebaseFirestore db;
    String phone, name, pass;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getDataById();
        db = FirebaseFirestore.getInstance();
        phone = edPhone.getText().toString();
        name = edName.getText().toString();
        pass = edPass.getText().toString();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot doc : querySnapshot) {
                                if (edPhone.getText().toString().equals(doc.getString("phone"))) {
                                    flag = false;
                                     Log.i("ketqua1",doc.getString("phone"));
                                     break;
                                } else {
                                    flag = true;
                                }
                            }
                            Log.i("ketqua", flag + "");
                            if (flag == true) {
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
                                Toast.makeText(getApplicationContext(), "Create Account Success", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"Phone number already exists",Toast.LENGTH_SHORT).show();
                            }

                        }
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
