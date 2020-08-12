package com.news.news.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.news.news.Adapter.PlansPagerAdapter;
import com.news.news.Models.Article;
import com.news.news.Models.Example;
import com.news.news.Models.Source;
import com.news.news.R;
import com.news.news.Support.ApiInterface;
import com.news.news.Support.ApiServiceGenerator;
import com.news.news.Support.connection;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tab;
    Integer len;
    ViewPager viewPager;
    Context context;
    Activity activity;
    ArrayList<Article> articleArrayList;
    Source source;
    com.news.news.Support.connection connection;
    ArrayList<String> sourceName=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context=MainActivity.this;
        activity=MainActivity.this;
        viewPager=findViewById(R.id.frameLayout);
        connection=new connection(activity);
        callApi();


    }

    private void callApi() {
        connection.showDialog();
        ApiInterface apiInterface= ApiServiceGenerator.createService().create(ApiInterface.class);
        Call<Example> call=apiInterface.callExmpApi();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                connection.hideDialog();
                if (response.code() == 200) {
                    Example example = response.body();
                    String status = example.getStatus();
                    //Toast.makeText(context, status, Toast.LENGTH_LONG).show();
                    if (!status.equals("error")) {
                        articleArrayList=new ArrayList<>();
                        articleArrayList=example.getArticles();
                        int leng=articleArrayList.size();
                        for(int i=0;i<leng;i++){
                            source=articleArrayList.get(i).getSource();
                            sourceName.add(source.getName()) ;
                        }
                        Log.d("ArraySource=",String.valueOf(sourceName));
                        HashSet<String> hashSet = new HashSet<String>();
                        hashSet.addAll(sourceName);
                        sourceName.clear();
                        sourceName.addAll(hashSet);
                        Log.d("ArraySource=",String.valueOf(sourceName));
                        len=sourceName.size();
                        callTabLayout(len,sourceName,articleArrayList);
                        Log.d("sizeofArr==",String.valueOf(len));

                    }
                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                connection.hideDialog();
                Toast.makeText(context, "Failure,Please check your network.", Toast.LENGTH_LONG).show();

            }


        });

    }

    private void callTabLayout(Integer len, ArrayList<String> sourceName, ArrayList<Article> articleArrayList) {
        Log.d("after_sizeofArr==",String.valueOf(len));
         for (int k = 0; k <len; k++) {
            tab.addTab(tab.newTab().setText(sourceName.get(k)));
        }

        PlansPagerAdapter adapter = new PlansPagerAdapter
                (getSupportFragmentManager(), tab.getTabCount(),sourceName,articleArrayList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    //Bonus Code : If your tab layout has more than 2 tabs then tab will scroll other wise they will take whole width of the screen
        if (tab.getTabCount() == 2) {
            tab.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }


}

