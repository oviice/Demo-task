package com.ovi.demotask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ovi.demotask.model.CategoriesItem;
import com.ovi.demotask.model.SubcatgItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<CategoriesItem> items;
    List<SubcatgItem> subCat;

    public CategoryAdapter(Context context, List<CategoriesItem> items) {
        this.context = context;
        this.items = items;
        subCat = new ArrayList<>();
        try {
            subCat.addAll(SharedPref.getArrayList("SUBCATEGORY"));
        } catch (Exception e) {
        }
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
        myViewHolder.recyclerViewSubCategory.setLayoutManager(new LinearLayoutManager(context));
        myViewHolder.recyclerViewSubCategory.setAdapter(new SubCategoryAdapter(context, items.get(i).getSubcatg()));
        myViewHolder.name.setText(items.get(i).getCategoryName());
        myViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myViewHolder.recyclerViewSubCategory.getVisibility() == View.VISIBLE) {
                    myViewHolder.recyclerViewSubCategory.setVisibility(View.GONE);
                    myViewHolder.hideBar.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_keyboard_arrow_right_24));
                } else {
                    myViewHolder.recyclerViewSubCategory.setVisibility(View.VISIBLE);
                    myViewHolder.hideBar.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24));
                }
            }
        });
        myViewHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.check.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_check_circle_24));
                subCat.addAll(items.get(i).getSubcatg());
                SharedPref.saveArrayList(subCat,"SUBCATEGORY");
                notifyDataSetChanged();

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
        //CardView layout;
        RecyclerView recyclerViewSubCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            hideBar = itemView.findViewById(R.id.hideBar);
            check = itemView.findViewById(R.id.check);
            recyclerViewSubCategory = itemView.findViewById(R.id.recyclerViewSubCategory);
//            layout = itemView.findViewById(R.id.layout);
        }
    }
}
