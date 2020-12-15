package com.example.app_ban_hang.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/*import com.example.app_ban_hang.NewDogAdapter;
import com.example.app_ban_hang.R;
import com.example.app_ban_hang.db.DogDao;
import com.example.app_ban_hang.db.DogDatabase;
import com.example.app_ban_hang.model.DogClass;
import com.example.app_ban_hang.viewmodel.DogApiService;*/
import com.example.app_ban_hang.ItemClickListener;
import com.example.app_ban_hang.detail.first_Detail_Layout;
import com.example.app_ban_hang.NewDogAdapter;
import com.example.app_ban_hang.R;
import com.example.app_ban_hang.db.DogDao;
import com.example.app_ban_hang.db.DogDatabase;
import com.example.app_ban_hang.model.DogClass;
import com.example.app_ban_hang.viewmodel.DogApiService;
import com.example.app_ban_hang.NewDogAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListDogFragment extends Fragment {
    private DogApiService apiService;
    private ArrayList<DogClass> dogs;
    private NewDogAdapter newDogAdapter;
    private RecyclerView recyclerView;
    private View v;
    private DogDao dogDao;
    private DogDatabase dogDatabase;
    private Context context;
    private int min = 2, min_money=100;
    private int max = 20, max_money=1000;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public ListDogFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v = inflater.inflate(R.layout.fragment_list_dog, container, false);
        context = v.getContext();
        apiService = new DogApiService();
        recyclerView = v.findViewById(R.id.rv_dog);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        dogs = new ArrayList<DogClass>();
        newDogAdapter = new NewDogAdapter(dogs, context);
        recyclerView.setAdapter(newDogAdapter);
        apiService = new DogApiService();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dogDatabase = DogDatabase.getInstance(getContext());
                dogDao = dogDatabase.dogDao();
                List<DogClass> dbdogs = dogDao.getAllDogs();
                if(dbdogs.isEmpty()){
                    apiService.getDogs()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<List<DogClass>>() {
                                @Override
                                public void onSuccess(@NonNull List<DogClass> Dog) {
                                    for(DogClass dog: Dog){
                                        Random r = new Random();
                                        dog.setDog_count(r.nextInt((max-min)+1)+min);
                                        dog.setMoney((r.nextInt((max_money-min_money)+1+min_money))*dog.getDog_count());
                                        dogs.add(dog);
                                        AsyncTask.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                dogDao.insertDog(dog);
                                            }
                                        });
                                    }
                                    newDogAdapter.notifyDataSetChanged();
                                }
                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Toast.makeText(context, "Error! Try again later!" , Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else{
                    for(DogClass dog: dbdogs){
                        dogs.add(dog);
                    }
                    newDogAdapter.notifyDataSetChanged();
                }

            }
        });

       newDogAdapter.setOnItemClick(new ItemClickListener() {
            @Override
            public void onClick(int position) {

                Intent intent= new Intent(getContext(),first_Detail_Layout.class);
                Bundle bundle=new Bundle();
              DogClass dog=dogs.get(position);
               bundle.putString("name",dog.getName());
                bundle.putString("life",dog.getLifeSpan());
               bundle.putString("origin",dog.getOrigin());
                bundle.putString("url",dog.getUrl());
               bundle.putString("bred_for",dog.getBred_for());
               bundle.putString("breed_group",dog.getBreed_group());
                bundle.putString("temperament",dog.getTemperament());
                bundle.putInt("productprice",dog.getMoney());
                bundle.putInt("position",position);
                bundle.putInt("Dog_count",dog.getDog_count());
              //  Toast.makeText(getActivity(),""+dog.getMoney(),Toast.LENGTH_SHORT).show();
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });



     //   newDogAdapter.setOnItemClick(new () {
      //      @Override
   //    public void onClick(int position) {
   //             Intent intent= new Intent(getContext(),first_Detail_Layout.class);
   //             startActivity(intent);

//                Bundle bundle=new Bundle();
//                DogClass dog=dogs.get(position);
//                bundle.putString("name",dog.getName());
//                bundle.putString("life",dog.getLifeSpan());
//                bundle.putString("origin",dog.getOrigin());
//                bundle.putString("url",dog.getUrl());
//                bundle.putString("bred_for",dog.getBred_for());
//                bundle.putString("breed_group",dog.getBreed_group());
//                bundle.putString("temperament",dog.getTemperament());
//                Navigation.findNavController(v).navigate(R.id.action_list_to_detail,bundle);
   //         }
     //  });

        ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            private final ColorDrawable background = new ColorDrawable(getResources().getColor(R.color.background));

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                newDogAdapter.showMenu(viewHolder.getAdapterPosition());
                if (newDogAdapter.isMenuShown()==false) {
                    newDogAdapter.closeMenu();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                View itemView = viewHolder.itemView;

                if (dX > 0) {
                    background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX), itemView.getBottom());
                } else if (dX < 0) {
                    background.setBounds(itemView.getRight() + ((int) dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                } else {
                    background.setBounds(0, 0, 0, 0);
                }

                background.draw(c);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.search_bar,menu);
        MenuItem mSearch=menu.findItem(R.id.appSearchBar);
        SearchView searchView=(SearchView)mSearch.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String name = s.toLowerCase();
                dogs.clear();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<DogClass> dbdog;
                        if(name.isEmpty()){
                            dbdog = dogDao.getAllDogs();
                        }
                        else{
                            dbdog = dogDao.searchByName(name);
                        }
                        for(DogClass dog: dbdog){
                            dogs.add(dog);
                        }
                    }
                });
                newDogAdapter.notifyDataSetChanged();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
