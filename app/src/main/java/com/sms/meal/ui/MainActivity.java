package com.sms.meal.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.sms.R;
import com.sms.meal.ui.add.AddMealActivity;
import com.sms.ui.SlidingTabLayout;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;

/**
 * Created by cchiappini on 08/03/2015.
 */
public class MainActivity extends ActionBarActivity {
    private static final int ADD_MEAL_REQUEST = 2;

    // Declaring Your View and Variables

    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private ImageButton addButton;
    private final CharSequence titles[]={"Book","Share"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, titles fot the Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(), titles);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Share my meal");
        setSupportActionBar(toolbar);

        // Setting the ViewPager For the SlidingTabsLayout
        //tabs.setViewPager(pager);
        addButton = (ImageButton)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMeal();
            }
        });

        addButton.setOutlineProvider(new ViewOutlineProvider() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void getOutline(View view, Outline outline) {
                int diameter = getResources().getDimensionPixelSize(R.dimen.diameter);
                outline.setOval(0, 0, diameter, diameter);
            }
        });
        addButton.setClipToOutline(true);

    }

    private void addNewMeal() {
        Intent newMealIntent = new Intent(this, AddMealActivity.class);
        startActivityForResult(newMealIntent, ADD_MEAL_REQUEST);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
