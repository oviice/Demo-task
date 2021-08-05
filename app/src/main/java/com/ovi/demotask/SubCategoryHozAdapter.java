package com.ovi.demotask;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ovi.demotask.model.SubcatgItem;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryHozAdapter extends RecyclerView.Adapter<SubCategoryHozAdapter.MyViewHolder> {
    Context context;
    List<SubcatgItem> items;
    List<SubcatgItem> subCat;
    int pos = -1;
    CheckInterface checkInterface;
    public SubCategoryHozAdapter(Context context, List<SubcatgItem> items,CheckInterface checkInterface) {
        this.context = context;
        this.items = items;
        this.checkInterface = checkInterface;
        subCat = new ArrayList<>();
        try {
            subCat.addAll(SharedPref.getArrayList("SUBCATEGORY"));
        }
        catch (Exception e){}
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.design_single_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.name.setText(items.get(i).getSubCategoryName());
        myViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   for (int j = 0; j < subCat.size(); j++) {
                        Log.d("sdfsdfsdf", "onClick: for");
                        if (items.get(i).getSubCategoryId().equals(subCat.get(j).getSubCategoryId())) {
                            pos=j;
                            Log.d("sfdsf", "onClick: " + subCat.get(j).getSubCategoryId() + " " + items.get(i).getSubCategoryId());
                        }
                    }
                    subCat.remove(pos);

                Log.d("sdfsdfsdf", "onClick: for"+SharedPref.getArrayList("SUBCATEGORY"));
                SharedPref.saveArrayList(subCat,"SUBCATEGORY");
                checkInterface.check();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
//            layout = itemView.findViewById(R.id.layout);
        }
    }
}
