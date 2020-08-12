package com.news.news.Adapter;

import com.news.news.DynamicFragment;
import com.news.news.Models.Article;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PlansPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<String> tabTitle;
    ArrayList<Article> articleArrayList;

    public PlansPagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<String> tabTitle, ArrayList<Article> articleArrayList) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabTitle = tabTitle;
        this.articleArrayList=articleArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return new DynamicFragment().newInstance(tabTitle.get(position),articleArrayList);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
