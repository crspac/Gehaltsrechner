package com.example.gehaltsrechner;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private SectionStatePageAdapter mSectionStatePageAdapter;
    private ViewPager mViewPager;
    private Stack<Integer> pageHistory;
    private int currentPage;
    private boolean saveToHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionStatePageAdapter = new SectionStatePageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);


        setupViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(5);

        pageHistory = new Stack<Integer>();
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if(saveToHistory)
                {
                    pageHistory.push(Integer.valueOf(currentPage));
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        saveToHistory = true;

    }

    @Override
    public void onBackPressed() {
        if (pageHistory.empty())
            super.onBackPressed();
        else {
            saveToHistory = false;
            mViewPager.setCurrentItem(pageHistory.pop().intValue());
            saveToHistory = true;
        }
    }

    private void setupViewPager(ViewPager viewPager){
        SectionStatePageAdapter adapter = new SectionStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHome(), "Home");
        adapter.addFragment(new FragmentOutput(), "Output");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
