package com.example.newsappyc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRvAdapter extends RecyclerView.Adapter<CategoryRvAdapter.ViewHoler> {

    private ArrayList<CategoryRvModal> categoryRvModals;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRvAdapter(ArrayList<CategoryRvModal> categoryRvModals, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRvModals = categoryRvModals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    //    public CategoryRvAdapter(ArrayList<CategoryRvModal> categoryRvModals, Context context, CategoryClickInterface categoryClickInterface) {
//        this.categoryRvModals = categoryRvModals;
//        this.context = context;
//        this.categoryClickInterface = categoryClickInterface;
//    }

    @NonNull
    @Override
    public CategoryRvAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categerious_rv_item,parent,false);
        return new CategoryRvAdapter.ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRvAdapter.ViewHoler holder,  int position) {
        CategoryRvModal categoryRvModal=categoryRvModals.get(position);
      //  CategoryRvModal categoryRvModal=categoryRvModals.get(position);
        holder.categoryTV.setText(categoryRvModal.getCategory());
        //image load
        Picasso.get().load(categoryRvModal.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRvModals.size();
    }
    //
    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private ImageView categoryIV;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            categoryTV=itemView.findViewById(R.id.idTextViewCategory);
            categoryIV=itemView.findViewById(R.id.idimgviewCategegory);
        }
    }
}
