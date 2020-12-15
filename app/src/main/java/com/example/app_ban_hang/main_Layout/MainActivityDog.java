package com.example.app_ban_hang.main_Layout;

import com.example.app_ban_hang.R;
import com.example.app_ban_hang.viewmodel.DogApiService;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import com.example.app_ban_hang.model.DogClass;
import com.example.app_ban_hang.viewmodel.DogApiService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityDog extends AppCompatActivity {
    private DogApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dog);
    }
}
