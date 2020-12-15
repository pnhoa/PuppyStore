package com.example.app_ban_hang.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_ban_hang.R;
import com.example.app_ban_hang.model.DatabaseAccessFavourite;
import com.example.app_ban_hang.model.DatabaseThanhToan;

import java.util.List;


public class activity_order_details extends AppCompatActivity {
    DatabaseAccessFavourite database;

    private TextView  count,tv_price_total;
    private String url,bd_Name,bd_LifeSpan,bd_Origin,bd_Bred_for,bd_Breed_group,bd_Temperament;
    private int price, no_of_items,price_total;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        mapping();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
     //   int Dog_count=bundle.getInt("Dog_count");
        if(bundle!=null)
            retrieveIntent(bundle);
        count.setText(""+no_of_items);
        tv_price_total.setText(""+price_total);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = DatabaseAccessFavourite.getInstance(getApplicationContext());
                database.open();
                List<String> fav_list = database.getWordsFavoutire();

               if (fav_list.contains(bd_Name))
                    Toast.makeText(getApplicationContext(), "ban da them vao phan thanh toan : ))", Toast.LENGTH_SHORT).show();
                else {
                    database.insertData(bd_Name);
                }
                database.close();
               Toast.makeText(getApplicationContext(), "ban da them vao phan thanh toan : ))", Toast.LENGTH_SHORT).show();
            }
        });


    }
    void mapping(){
        img =findViewById(R.id.img_add);
        count=findViewById(R.id.no_of_items);
        tv_price_total=findViewById(R.id.total_amount);

    }
    void retrieveIntent(Bundle bundle){
         no_of_items=bundle.getInt("number_item");
         price_total=bundle.getInt("price_total");

        url=bundle.getString("url");
        bd_Name=bundle.getString("name");
        bd_LifeSpan=bundle.getString("life");
        bd_Origin=bundle.getString("origin");
        bd_Bred_for=bundle.getString("bred_for");
        bd_Breed_group=bundle.getString("breed_group");
        bd_Temperament=bundle.getString("temperament");
        price=bundle.getInt("productprice");












    }
}
