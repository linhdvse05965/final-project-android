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
    int flagNum;

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
                                    flagNum = 1;
                                    Log.i("ketqua1", doc.getString("phone"));
                                    break;
                                } else if (edPhone.getText().toString().isEmpty()) {
                                    flagNum = 2;
                                    break;
                                } else if (edName.getText().toString().isEmpty()) {
                                    flagNum = 3;
                                    break;
                                } else if (edPass.getText().toString().isEmpty()) {
                                    flagNum = 4;
                                    break;
                                } else if (edPhone.getText().length() < 10) {
                                    flagNum = 5;
                                    break;
                                } else if (edPass.getText().length() < 6) {
                                    flagNum = 6;
                                    break;
                                } else {
                                    flagNum = 0;
                                    // flag = true;
                                }
                            }
                            Log.i("ketqua", flag + "");
                            if (flagNum == 0) {
                                Map<String, Object> createAccount = new HashMap<>();
                                createAccount.put("phone", edPhone.getText().toString());
                                createAccount.put("name", edName.getText().toString());
                                createAccount.put("pass", edPass.getText().toString());
                                db.collection("User").add(createAccount).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        finish();
                                    }
                                });
                                Toast.makeText(getApplicationContext(), "Create Account Success", Toast.LENGTH_SHORT).show();
                            } else if (flagNum == 1) {
                                Toast.makeText(getApplicationContext(), "Phone number already exists", Toast.LENGTH_SHORT).show();
                            } else if (flagNum == 2) {
                                Toast.makeText(getApplicationContext(), "Phone number can't empty", Toast.LENGTH_SHORT).show();
                            } else if (flagNum == 3) {
                                Toast.makeText(getApplicationContext(), "Name can't empty", Toast.LENGTH_SHORT).show();
                            } else if (flagNum == 4) {
                                Toast.makeText(getApplicationContext(), "Password can't empty", Toast.LENGTH_SHORT).show();
                            } else if (flagNum == 5) {
                                Toast.makeText(getApplicationContext(), "Number phone must be greater than 10 characters ", Toast.LENGTH_SHORT).show();
                            }else if (flagNum == 6) {
                                Toast.makeText(getApplicationContext(), "Password must be greater than 6 characters ", Toast.LENGTH_SHORT).show();
                            }
//                            if (flag == true) {
//                                Map<String,Object> createAccount =  new HashMap<>();
//                                createAccount.put("phone",edPhone.getText().toString());
//                                createAccount.put("name",edName.getText().toString());
//                                createAccount.put("pass",edPass.getText().toString());
//                                db.collection("User").add(createAccount).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                    @Override
//                                    public void onSuccess(DocumentReference documentReference) {
//                                        finish();
//                                    }
//                                });
//                                Toast.makeText(getApplicationContext(), "Create Account Success", Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(getApplicationContext(),"Phone number already exists",Toast.LENGTH_SHORT).show();
//                            }

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
