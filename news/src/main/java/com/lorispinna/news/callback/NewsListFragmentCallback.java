package com.lorispinna.news.callback;

import com.lorispinna.news.R;
import com.lorispinna.news.fragments.NewsListFragment;
import com.lorispinna.news.utils.Callback;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NewsListFragmentCallback implements Callback {
    FragmentManager fragmentManager;
    public NewsListFragmentCallback(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void call() {
        NewsListFragment fragment = new NewsListFragment();
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.app_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
