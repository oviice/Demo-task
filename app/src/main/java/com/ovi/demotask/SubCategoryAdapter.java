package com.ovi.demotask;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ovi.demotask.model.CategoriesItem;
import com.ovi.demotask.model.SubcatgItem;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    Context context;
    List<SubcatgItem> items;
    List<SubcatgItem> subCat;
    int pos = -1;

    public SubCategoryAdapter(Context context, List<SubcatgItem> items) {
        this.context = context;
        this.items = items;
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
        View view = layoutInflater.inflate(R.layout.design_category, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.name.setText(items.get(i).getSubCategoryName());
        myViewHolder.hideBar.setVisibility(View.GONE);
        for (int j=0;j<subCat.size();j++){
            try {
                if (subCat.get(j).getSubCategoryId().equals(items.get(i).getSubCategoryId())){
                    myViewHolder.check.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_check_circle_24));
                }
            }
            catch (Exception e){}
        }
        myViewHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.check.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.ic_baseline_check_circle_24).getConstantState()) {
                    for (int j = 0; j < subCat.size(); j++) {
                        Log.d("sdfsdfsdf", "onClick: for");
                        if (items.get(i).getSubCategoryId().equals(subCat.get(j).getSubCategoryId())) {
                            pos=j;
                            Log.d("sfdsf", "onClick: " + subCat.get(j).getSubCategoryId() + " " + items.get(i).getSubCategoryId());
                        }
                    }
                    subCat.remove(pos);
                    myViewHolder.check.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_unchecked_24));
                } else {
                    myViewHolder.check.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_check_circle_24));
                    SubcatgItem subcatgItem = new SubcatgItem();
                    subcatgItem.setSubCategoryId(items.get(i).getSubCategoryId());
                    subcatgItem.setSubCategoryName(items.get(i).getSubCategoryName());
                    subCat.add(subcatgItem);

                }
                Log.d("sdfsdfsdf", "onClick: for"+SharedPref.getArrayList("SUBCATEGORY"));
                SharedPref.saveArrayList(subCat,"SUBCATEGORY");
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
        ImageView hideBar, check;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            hideBar = itemView.findViewById(R.id.hideBar);
            check = itemView.findViewById(R.id.check);
//            layout = itemView.findViewById(R.id.layout);
        }
    }
}
