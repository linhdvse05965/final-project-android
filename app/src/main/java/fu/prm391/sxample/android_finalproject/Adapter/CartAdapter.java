package fu.prm391.sxample.android_finalproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fu.prm391.sxample.android_finalproject.Model.Category;
import fu.prm391.sxample.android_finalproject.Model.Order;
import fu.prm391.sxample.android_finalproject.R;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Order> arrayList;

    public CartAdapter(Context context, ArrayList<Order> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.cart_layout, null);
        Order o = arrayList.get(i);
        TextView txtItemName = view.findViewById(R.id.cart_item_name);
        TextView txtItemPrice = view.findViewById(R.id.cart_item_price);
        ImageView imageViewCount = view.findViewById(R.id.cart_item_count);
        TextDrawable drawable = (TextDrawable) TextDrawable.builder().buildRound(o.getQuantity(), Color.RED);
        imageViewCount.setImageDrawable(drawable);
        txtItemName.setText(o.getProductName());
        txtItemPrice.setText("$"+o.getPrice());
        return view;
    }
}
