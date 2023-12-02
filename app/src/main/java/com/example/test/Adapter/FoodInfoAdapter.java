package com.example.test.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Food;
import com.example.test.databinding.ItemfoodinfoBinding;

import java.util.List;

public class FoodInfoAdapter extends RecyclerView.Adapter<FoodInfoAdapter.FoodInfoViewHolder> {

    private final Context context;
    private final fooditemclick listener;
    private final List<Food> list;

    public FoodInfoAdapter(Context context, List<Food> list, fooditemclick listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public void setList(List<Food> updatefoodinfo) {
        list.clear();
        list.addAll(updatefoodinfo);
        notifyDataSetChanged();
    }

    public class FoodInfoViewHolder extends RecyclerView.ViewHolder {

        private final ItemfoodinfoBinding binding;

        public FoodInfoViewHolder(ItemfoodinfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public FoodInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ItemfoodinfoBinding binding = ItemfoodinfoBinding.inflate(inflater, parent, false);
        return new FoodInfoViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull FoodInfoViewHolder holder, int position) {
        Food food = list.get(position);
        ItemfoodinfoBinding binding = holder.binding;
        byte[] image = food.getImageuri();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        binding.recyclerviewtypeMeal.setText(food.getTypemeal());
        binding.recyclerviewfoodName.setText(food.getFoodname());
        binding.recyclerviewKcal.setText(food.getCal() + " Kcal");
        binding.recyclerviewfoodImage.setImageBitmap(bitmap);

        binding.worditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface fooditemclick {
        void onClick(Food item);
    }
}
