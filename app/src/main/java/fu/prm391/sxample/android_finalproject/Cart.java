package fu.prm391.sxample.android_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fu.prm391.sxample.android_finalproject.Adapter.CartAdapter;
import fu.prm391.sxample.android_finalproject.Adapter.CategoryAdapter;
import fu.prm391.sxample.android_finalproject.Model.Category;
import fu.prm391.sxample.android_finalproject.Model.Order;

public class Cart extends AppCompatActivity {
    ListView listView;
    Button btnPlaceOrder;
    TextView txtViewTotal;
    CartAdapter cartAdapter;
    ArrayList<Order> arrayList;
    FirebaseFirestore db;
    int Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getDataById();
        db = FirebaseFirestore.getInstance();
        loadCart();

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }

    public void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Please enter your address: ");
        final EditText editTextAdress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editTextAdress.setLayoutParams(lp);
        alertDialog.setView(editTextAdress);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = getIntent();
                String phone = intent.getStringExtra("phone");
                //add ship
                Map<String, Object> shipOrder = new HashMap<>();
                shipOrder.put("phone", phone);
                shipOrder.put("address", editTextAdress.getText().toString());
                if(editTextAdress.getText().toString().isEmpty()){
                    Toast.makeText(Cart.this,"Address can't be empty,can't Place Order",Toast.LENGTH_SHORT).show();
                }else {
                    db.collection("ShipOrder").add(shipOrder).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                        }
                    });
                    //clean cart
                    db.collection("Order").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                for (QueryDocumentSnapshot doc : querySnapshot) {
                                    doc.getReference().delete();
                                }
                            }
                        }
                    });
                    finish();
                }
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }

    private void loadCart() {
        db.collection("Order").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        String id = doc.getString("Id");
                        String name = doc.getString("Name");
                        String quantity = doc.getString("Quantity");
                        String price = doc.getString("Price");
                        arrayList.add(new Order(id, name, quantity, price));
                        Total += (Integer.parseInt(price)) * (Integer.parseInt(quantity));
                        Log.i("ketqua", Total + "");
                    }
                    txtViewTotal.setText("$" + String.valueOf(Total));
                    if(Total==0){
                        btnPlaceOrder.setEnabled(false);
                    }
                }
                listView.setAdapter(cartAdapter);
            }
        });
    }


    private void getDataById() {
        listView = findViewById(R.id.listCart);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        txtViewTotal = findViewById(R.id.total);
        arrayList = new ArrayList<Order>();
        cartAdapter = new CartAdapter(Cart.this, arrayList);
        listView.setAdapter(cartAdapter);
    }
}
