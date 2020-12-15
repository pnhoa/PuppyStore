package com.example.app_ban_hang.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_ban_hang.R;
import com.example.app_ban_hang.model.DatabaseAccessFavourite;

import java.util.List;

public class giohang extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    private ListView listView,lv_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);



        this.listView = (ListView) findViewById(R.id.lv_giohang);
        DatabaseAccessFavourite databaseAccess = DatabaseAccessFavourite.getInstance(this);
        databaseAccess.open();
        List<String> anhViet = databaseAccess.getWordsFavoutire();
        databaseAccess.close();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, anhViet);
        this.listView.setAdapter(adapter);





    }
}
