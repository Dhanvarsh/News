package com.news.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.news.Adapter.RecyclerAdapter;
import com.news.news.GroupModel.Data;
import com.news.news.GroupModel.Detail;
import com.news.news.Models.Article;

import java.util.ArrayList;


public class DynamicFragment extends Fragment {

    View view;
    String val;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ArrayList<Article> articleArrayList=new ArrayList<>();
    ArrayList<Data> dataArrayList;
    Data data=new Data();
    Detail detail=new Detail();

    public static DynamicFragment newInstance(String val, ArrayList<Article> articleArrayList) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString("someInt", val);
        args.putSerializable("array", articleArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_dynamic, container, false);
        ButterKnife.bind(this,view);
        val = getArguments().getString("someInt");
        articleArrayList=(ArrayList<Article>)getArguments().getSerializable("array");
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        makeList();
        return view;
    }

    private void makeList() {
        dataArrayList=new ArrayList<>();
        for(int i=0;i<articleArrayList.size();i++){
            String name=articleArrayList.get(i).getSource().getName();
            if(name.equals(val)){
                data=new Data();
                data.setTitle(articleArrayList.get(i).getTitle());
                data.setUrl(articleArrayList.get(i).getUrl());
                data.setDescription(articleArrayList.get(i).getDescription());
                data.setPublishedAt(articleArrayList.get(i).getPublishedAt());
                data.setUrlToImage(articleArrayList.get(i).getUrlToImage());
                dataArrayList.add(data);

            }
        }

        detail.setData(dataArrayList);
        RecyclerAdapter adapter=new RecyclerAdapter(getContext(),dataArrayList);
        recyclerView.setAdapter(adapter);
    }
}