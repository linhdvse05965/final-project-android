package fu.prm391.sxample.android_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import fu.prm391.sxample.android_finalproject.Adapter.CartAdapter;
import fu.prm391.sxample.android_finalproject.Adapter.ShipOrderAdapter;
import fu.prm391.sxample.android_finalproject.Model.Order;
import fu.prm391.sxample.android_finalproject.Model.ShipOrder;

public class ShipOrderActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ShipOrder> arrayList;
    FirebaseFirestore db;
    ShipOrderAdapter shipOderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_order);
        getDataById();
        db = FirebaseFirestore.getInstance();
        loadShipOrder();

    }

    public void loadShipOrder(){
        db.collection("ShipOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        String phone = doc.getString("phone");
                        String address = doc.getString("address");;
                        arrayList.add(new ShipOrder(phone,address));
                    }

                }
                listView.setAdapter(shipOderAdapter);
            }
        });
    }

    private void getDataById() {
        listView = findViewById(R.id.listShipOrder);
        arrayList = new ArrayList<ShipOrder>();
        shipOderAdapter = new ShipOrderAdapter(ShipOrderActivity.this, arrayList);
        listView.setAdapter(shipOderAdapter);
    }
}
