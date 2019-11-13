package fu.prm391.sxample.android_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

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
    }

    private void loadCart() {
        db.collection("Order").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for(QueryDocumentSnapshot doc : querySnapshot){
                        String id = doc.getString("Id");
                        String name = doc.getString("Name");
                        String quantity = doc.getString("Quantity");
                        String price = doc.getString("Price");
                        arrayList.add(new Order(id,name,quantity,price));
                        Total +=(Integer.parseInt(price))*(Integer.parseInt(quantity));
                        Log.i("ketqua",Total+"");
                    }
                    txtViewTotal.setText(String.valueOf(Total));
                }
                listView.setAdapter(cartAdapter);
            }
        });
    }


    private void getDataById(){
        listView = findViewById(R.id.listCart);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        txtViewTotal = findViewById(R.id.total);
        arrayList = new ArrayList<Order>();
        cartAdapter = new CartAdapter(Cart.this, arrayList);
        listView.setAdapter(cartAdapter);
    }
}
