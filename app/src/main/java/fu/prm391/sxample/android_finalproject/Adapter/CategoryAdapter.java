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
import fu.prm391.sxample.android_finalproject.R;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Category> arrayList;

    public CategoryAdapter(Context context, ArrayList<Category> arrayList) {
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
        view = inflater.inflate(R.layout.menu_item, null);
        Category c = arrayList.get(i);
        TextView txtMenuName = view.findViewById(R.id.menu_name);
        ImageView imageView = view.findViewById(R.id.menu_image);

        txtMenuName.setText(c.getName());
        Picasso.with(context).load(c.getImage()).into(imageView);
        return view;
    }
}
