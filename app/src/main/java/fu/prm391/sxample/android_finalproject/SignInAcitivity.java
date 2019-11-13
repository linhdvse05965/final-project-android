package fu.prm391.sxample.android_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInAcitivity extends AppCompatActivity {
    EditText editTextPhone, editTextPass;
    Button btnSignIn;
    FirebaseFirestore db;
    boolean flag;
    String name;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_acitivity);
        getDatabyId();
        db = FirebaseFirestore.getInstance();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for(QueryDocumentSnapshot doc : querySnapshot){
                                if((editTextPhone.getText().toString().equals(doc.getString("phone"))) && (editTextPass.getText().toString().equals(doc.getString("pass")))){
                                    flag = true;
                                    name = doc.getString("name");
                                    phone = doc.getString("phone");
                                    break;
                                }else{
                                    flag = false;
                                }
                            }
                            if (flag == true) {
                                Intent intentHome = new Intent(SignInAcitivity.this, Home.class);
                                intentHome.putExtra("name",name);
                                intentHome.putExtra("phone",phone);
                                startActivity(intentHome);
                            } else {
                                Toast.makeText(getApplicationContext(), "Incorrect Phone number or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }

    private void getDatabyId() {
        editTextPass = findViewById(R.id.edPassSignIn);
        editTextPhone = findViewById(R.id.edPhoneSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);
    }
}
