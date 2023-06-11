package com.example.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Order_Detail extends AppCompatActivity {
    ImageView image,add,remove;
    TextView Productname,TotalPrice;
    TextView Iteamcount,Productprice;
    Button Order;
    int qun=1,Total=0,price=0;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().hide();
        Intent i=getIntent();
        String imageurl=i.getStringExtra("Productimage");
        Productname=findViewById(R.id.ordername);
        Productprice=findViewById(R.id.productpriceok);
        TotalPrice=findViewById(R.id.Totalprice);
        Iteamcount=findViewById(R.id.itemcount);
        Order=findViewById(R.id.orderbutton);
        image=findViewById(R.id.orderiage);
        add=findViewById(R.id.add);
        remove=findViewById(R.id.remove);


        Productname.setText(i.getStringExtra("Productname"));
        Productprice.setText(i.getStringExtra("Productprice"));
        Picasso.get().load(i.getStringExtra("Productimage")).into(image);
        Iteamcount.setText("1");
        TotalPrice.setText(i.getStringExtra("Productprice"));

        price=Integer.parseInt(Productprice.getText().toString());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qun=qun+1;
                Iteamcount.setText(String.valueOf(qun));
                Total=qun*price;
                TotalPrice.setText(String.valueOf(Total));
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qun>1){

                    qun=qun-1;
                }
                else{
                    qun=1;
                }
                Iteamcount.setText(String.valueOf(qun));
                Total=qun*price;
                TotalPrice.setText(String.valueOf(Total));
            }
        });
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(Order_Detail.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.p_l_1);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new CountDownTimer(5000,1000){

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        Intent intent=new Intent(Order_Detail.this,Detail.class);
                        intent.putExtra("TOTAL",TotalPrice.getText().toString());
                        intent.putExtra("NAME",Productname.getText().toString());
                        intent.putExtra("QUN",Iteamcount.getText().toString());
                        intent.putExtra("PRICE",Productprice.getText().toString());
                        intent.putExtra("URL",imageurl);
                        startActivity(intent);
                        finish();
                    }
                }.start();
//
//                Intent intent=new Intent(Order_Detail.this,Detail.class);
//                intent.putExtra("TOTAL",TotalPrice.getText().toString());
//                intent.putExtra("QUN",Iteamcount.getText().toString());
//                intent.putExtra("PRICE",Productprice.getText().toString());
//                intent.putExtra("URL",imageurl);
//                startActivity(intent);
//                finish();

            }
        });


    }
}