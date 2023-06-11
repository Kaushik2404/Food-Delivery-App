package com.example.fooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements RecyclerviewInterface {
    RecyclerView recyclerView;

    Myadapter myadapter;
    ArrayList<Product> list;
    ImageView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        getSupportActionBar().setTitle("Home");
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().hide();


        logout=findViewById(R.id.logout);
        recyclerView=findViewById(R.id.productlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myadapter=new Myadapter(this,list,this);
        recyclerView.setAdapter(myadapter);

       // int[] imagesok = {R.drawable.delivery4image,R.drawable.delivery3,R.drawable.delivery5image,R.drawable.delivery6image};
        // slider
      // slider=findViewById(R.id.slider);

//        List<SlideModel> slideModels=new ArrayList<>();
//
//        slideModels.add(new SlideModel(R.drawable.image));
//        slideModels.add(new SlideModel("https://images.app.goo.gl/ZLZdJotKAp4SYFn17 "));
//
//       slider.setImageList(slideModels,true);


        // Initializing the ViewPager Object
        //mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        // Initializing the ViewPagerAdapter
        //mViewPagerAdapter = new ViewPagerAdapter(imagesok,Home.this);

        // Adding the Adapter to the ViewPager
       // mViewPager.setAdapter(mViewPagerAdapter);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(Home.this,Login.class);
                finish();

            }
        });

        FirebaseDatabase.getInstance().getReference().child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Product product = snapshot1.getValue(Product.class);
                    list.add(product);
                }
               myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

//        list.add(new Product(R.drawable.dhosa,"Dhosa","120"));
//        list.add(new Product(R.drawable.idli,"Idli","100"));
//        list.add(new Product(R.drawable.panjabi,"Panjabi Thali","80"));
//        list.add(new Product(R.drawable.povbhaji,"Pov Bhaji","50"));
//        list.add(new Product(R.drawable.combo,"Combo","150"));
//        list.add(new Product(R.drawable.pizza,"Pizza","220"));
//        list.add(new Product(R.drawable.samosa,"Samosa","50"));
//        list.add(new Product(R.drawable.gujrati,"Gujrati thali","200"));


//        myadapter=new Myadapter(this,list);
//        recyclerView.setAdapter(myadapter);
        });

    }

    @Override
    public void onItemClick(int position) {

        Intent intent =new Intent(Home.this,Order_Detail.class);
        intent.putExtra("Productname",list.get(position).getProductname());
        intent.putExtra("Productprice",list.get(position).getProductprice());
        intent.putExtra("Productimage",list.get(position).getImage());
        startActivity(intent);


    }


}