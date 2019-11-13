package fu.prm391.sxample.android_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import fu.prm391.sxample.android_finalproject.Model.Category;
import fu.prm391.sxample.android_finalproject.Model.Order;

public class FoodDetailActivity extends AppCompatActivity {
    TextView food_name, food_price, food_des;
    ImageView food_image;
    FloatingActionButton btnCart;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ElegantNumberButton numberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        getDataById();
        Intent intent = getIntent();
        final Category c = (Category) intent.getSerializableExtra("Category");
        final String foodId = c.getId();
        food_name.setText(c.getName());
        food_price.setText(c.getPrice());
        food_des.setText(c.getDescription());
        Picasso.with(FoodDetailActivity.this).load(c.getImage()).into(food_image);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new DBHelper(getBaseContext()).addToCart(new Order(foodId,food_name.toString(),numberButton.getNumber(),food_price.toString()));
//                //Log.i("ketqua",foodId + "" + c.getName() + "" + numberButton.getNumber() + "" + c.getPrice());
              //  Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_SHORT).show();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String,Object> orderFood =  new HashMap<>();
                orderFood.put("Id",foodId);
                orderFood.put("Name",c.getName());
                orderFood.put("Quantity",numberButton.getNumber());
                orderFood.put("Price",c.getPrice());
                db.collection("Order").add(orderFood).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void getDataById(){
        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        food_des = findViewById(R.id.food_description);
        food_image = findViewById(R.id.img_food);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapedAppbar);
        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

    }
}
