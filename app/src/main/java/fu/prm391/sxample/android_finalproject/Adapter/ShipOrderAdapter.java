package fu.prm391.sxample.android_finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fu.prm391.sxample.android_finalproject.Model.Category;
import fu.prm391.sxample.android_finalproject.Model.ShipOrder;
import fu.prm391.sxample.android_finalproject.R;

public class ShipOrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<ShipOrder> arrayList;

    public ShipOrderAdapter(Context context, ArrayList<ShipOrder> arrayList) {
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
        view = inflater.inflate(R.layout.shiporder_layout, null);
        ShipOrder s = arrayList.get(i);
        TextView txtPhone = view.findViewById(R.id.txtShipPhone);
        TextView txtAddress = view.findViewById(R.id.txtAddressShip);


        txtPhone.setText("Phone Number: "+s.getPhone());
        txtAddress.setText("Address: "+s.getAddress());
        return view;
    }
}
