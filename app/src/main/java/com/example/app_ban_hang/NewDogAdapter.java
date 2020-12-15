package com.example.app_ban_hang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_hang.model.DogClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewDogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static ItemClickListener listener;
    private static Context mContext;
    private static ArrayList<DogClass> dogClass;
    private static ArrayList<DogClass> mdogClass;
    private final int SHOW_MENU = 1;
    private final int HIDE_MENU = 2;

    public NewDogAdapter(ArrayList<DogClass> dogClass, Context mContext){
        this.dogClass = dogClass;
        this.mContext = mContext;
        this.mdogClass = new ArrayList<>(dogClass);
    }
    @NonNull

    @Override
    public int getItemViewType(int position) {
        if(dogClass.get(position).isShowMenu()){
            return SHOW_MENU;
        }else{
            return HIDE_MENU;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(viewType==SHOW_MENU){
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item_detail, parent, false);
            return new MenuViewHolder(v);
        }else{
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
            return new MyViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            ((MyViewHolder) holder).name_dog.setText(dogClass.get(position).getName());
            ((MyViewHolder) holder).origin_dog.setText("Origin: " + ((dogClass.get(position).getOrigin() == null ||
                    (dogClass.get(position).getOrigin().toString().isEmpty()))
                    ? "Unknown" : dogClass.get(position).getOrigin()));
            ((MyViewHolder) holder).lifespan_dog.setText("Life_span: " + dogClass.get(position).getLifeSpan());
            Picasso.get().load(dogClass.get(position).getUrl()).placeholder(R.drawable.progress_animation).
                    resize(180, 180).centerCrop().into(((MyViewHolder) holder).img_dog);
            ((MyViewHolder) holder).dogs_item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showMenu(position);
                    return true;
                }
            });
        }

        if(holder instanceof MenuViewHolder){
            //Menu Actions
            ((MenuViewHolder) holder).name_dog_detail.setText(dogClass.get(position).getName());
            ((MenuViewHolder) holder).breed_group_dog_detail.setText("Breed_group: " + dogClass.get(position).getBreed_group());
            ((MenuViewHolder) holder).temperament_dog_detail.setText("Temperament: "+ dogClass.get(position).getTemperament());
            ((MenuViewHolder) holder).dogs_item_detail.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    closeMenu();
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dogClass.size();
    }

    public void setOnItemClick(ItemClickListener listener){
        this.listener=listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public View view;
        private LinearLayout dogs_item;
        private ImageView img_dog;
        private TextView name_dog;
        private TextView origin_dog;
        private TextView lifespan_dog;
        public MyViewHolder(View view){
            super(view);
            this.view = view;
            img_dog = view.findViewById(R.id.img_dog);
            name_dog = view.findViewById(R.id.name_dog);
            origin_dog = view.findViewById(R.id.origin_dog);
            lifespan_dog = view.findViewById(R.id.lifespan_dog);
            dogs_item = view.findViewById(R.id.dogs_item);
            dogs_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        listener.onClick(getAdapterPosition());
                    }
                }
            });
        }
    }
    //Our menu view
    public class MenuViewHolder extends RecyclerView.ViewHolder{
        public View view;
        private LinearLayout dogs_item_detail;
        private TextView name_dog_detail;
        private TextView breed_group_dog_detail;
        private TextView temperament_dog_detail;
        public MenuViewHolder(View view){
            super(view);
            this.view = view;
            name_dog_detail = view.findViewById(R.id.name_dog_detail);
            breed_group_dog_detail = view.findViewById(R.id.breed_group_dog_detail);
            dogs_item_detail = view.findViewById(R.id.dogs_item_detail);
            temperament_dog_detail = view.findViewById(R.id.temperament_dog_detail);
            dogs_item_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        listener.onClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void showMenu(int position) {
        for(int i=0; i<dogClass.size(); i++){
            dogClass.get(i).setShowMenu(false);
        }
        dogClass.get(position).setShowMenu(true);
        notifyDataSetChanged();
    }


    public boolean isMenuShown() {
        for(int i=0; i<dogClass.size(); i++){
            if(dogClass.get(i).isShowMenu()){
                return true;
            }
        }
        return false;
    }

    public void closeMenu() {
        for(int i=0; i<dogClass.size(); i++){
            dogClass.get(i).setShowMenu(false);
        }
        notifyDataSetChanged();
    }
}
