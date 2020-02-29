package com.example.marsplay;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marsplay.Models.Doc;
import com.example.marsplay.Models.Model;
import com.example.marsplay.interfaces.interf;
import com.example.marsplay.recycler.Adapter_Model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    private List<Doc> mylist=new ArrayList<Doc>();
    TextView numfound,score,start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MINUTES)
                .readTimeout(10000, TimeUnit.MINUTES)
                .writeTimeout(100, TimeUnit.MINUTES)
                .build();

        recyclerView=findViewById(R.id.recycler);
        numfound=findViewById(R.id.numfound);
        score=findViewById(R.id.maxscore);
        start=findViewById(R.id.start);
       // text=findViewById(R.id.text);
        String str="";
        Retrofit retrofit=new Retrofit.Builder().baseUrl(interf.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        interf api=retrofit.create(interf.class);
        boolean internet=isInternetAvailable();
        if(internet==false)
            Toast.makeText(MainActivity.this,"Please Connect to Internet",Toast.LENGTH_SHORT).show();


        api.getmodel().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                int sizee=response.body().reesponse.getDocs().size();
               numfound.setText("Number Found: "+response.body().reesponse.getNumFound().toString());
                score.setText("Max Score: "+response.body().reesponse.getMaxScore().toString());
                start.setText("Score: "+response.body().reesponse.getStart().toString());
                //Toast.makeText(MainActivity.this,String.valueOf(sizee),Toast.LENGTH_SHORT).show();
                for(int i=0;i<sizee;i++) {
                 //   Toast.makeText(MainActivity.this,response.body().reesponse.getDocs().get(i).getAbstract().get(0),Toast.LENGTH_SHORT).show();
                    Doc doc=new Doc(response.body().reesponse.getDocs().get(i).getJournal(),response.body().reesponse.getDocs().get(i).getArticleType(),response.body().reesponse.getDocs().get(i).getAbstract());
                    mylist.add(doc);


                }
                Adapter_Model adapter_model=new Adapter_Model(getApplicationContext(),mylist);
                adapter_model.notifyDataSetChanged();
                recyclerView.setAdapter(adapter_model);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

             //  text.setText(response.body().reesponse.docs.get(0).getPublicationDate().toString());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
               // text.setText(t.getMessage());

                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }



}
