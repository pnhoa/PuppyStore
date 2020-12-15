package com.example.app_ban_hang.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_ban_hang.model.DatabaseAccessFavourite;
import com.example.app_ban_hang.model.DogClass;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_ban_hang.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class first_Detail_Layout extends AppCompatActivity {
    private static ArrayList<DogClass> dogClass;
    private Button bt,btn_decrease,btn_increase;
    private ImageView img;
    private TextView productName,edit_quantity,add_bag;
    private TextView productPrice,lifeSpan,origin,temperament,bred_for,breed_group;
    private int price;
    private String bd_Name,bd_LifeSpan,bd_Origin,url,bd_Bred_for,bd_Breed_group,bd_Temperament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_product);
        bt = findViewById(R.id.buy_now);
        mapping();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int Dog_count=bundle.getInt("Dog_count");
        if(bundle!=null)
        retrieveIntent(bundle);
        edit_quantity.setText("1");
        add_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseAccessFavourite database;
                database = DatabaseAccessFavourite.getInstance(getApplicationContext());
                database.open();
                List<String> fav_list = database.getWordsFavoutire();
                if (fav_list.contains(bd_Name))
                    Toast.makeText(getApplicationContext(), "ban da them vao phan gio hang : ))", Toast.LENGTH_SHORT).show();
                else {
                    database.insertData(bd_Name);
                }
                database.close();





            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), activity_order_details.class);
                Bundle bundle2=new Bundle();

                int number_item=Integer.parseInt(edit_quantity.getText().toString());

                int price_total=price*number_item;
                bundle2.putInt("number_item",number_item);
                bundle2.putInt("price_total",price_total);

                bundle2.putString("name",bd_Name);
                bundle2.putString("life",bd_LifeSpan);
                bundle2.putString("origin",bd_Origin);
                bundle2.putString("url",url);
                bundle2.putString("bred_for",bd_Bred_for);
                bundle2.putString("breed_group",bd_Breed_group);
                bundle2.putString("temperament",bd_Temperament);
                bundle2.putInt("productprice",price);










                intent2.putExtras(bundle2);
                                startActivity(intent2);
            }
        });
        btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempt=Integer.parseInt(edit_quantity.getText().toString());
                tempt=tempt+1;
                edit_quantity.setText(""+tempt);

            }
        });
        btn_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempt=Integer.parseInt(edit_quantity.getText().toString());
                if(tempt>1){
                    tempt=tempt-1;
                    edit_quantity.setText(""+tempt);

                }
            }
        });

    }
    public void retrieveIntent(Bundle bundle){
         url=bundle.getString("url");


        Picasso.get().load(url).placeholder(R.drawable.progress_animation).
                resize(180, 180).centerCrop().into(img);
        bd_Name=bundle.getString("name");
        bd_LifeSpan=bundle.getString("life");
        bd_Origin=bundle.getString("origin");
        bd_Bred_for=bundle.getString("bred_for");
        bd_Breed_group=bundle.getString("breed_group");
        bd_Temperament=bundle.getString("temperament");
        price=bundle.getInt("productprice");

        productName.setText(bundle.getString("name"));

        lifeSpan.setText(bundle.getString("life"));
       origin.setText(bundle.getString("origin"));
        bred_for.setText(bundle.getString("bred_for"));
        breed_group.setText(bundle.getString("breed_group"));
        temperament.setText((bundle.getString("temperament")));

        productPrice.setText("$"+price);







    }
    public void mapping(){
        add_bag=findViewById(R.id.add_to_cart);
        edit_quantity=findViewById(R.id.ed_quantity);
        btn_decrease=findViewById(R.id.btn_decrement);
        btn_increase=findViewById(R.id.btn_increment);
        lifeSpan=findViewById(R.id.lifeSpan);
        origin=findViewById(R.id.origin);
        temperament=findViewById(R.id.temperament);
        bred_for=findViewById(R.id.bred_for);
        breed_group=findViewById(R.id.breed_group);
        productName=findViewById(R.id.productname);
        productPrice=findViewById(R.id.productprice);
        img =findViewById(R.id.img_dog_individual);

    }
}
