package com.bobpaulin.shuffletag;

import java.util.Locale;

import com.bobpaulin.shuffletag.callbacks.SetHostnameCallback;
import com.bobpaulin.shuffletag.callbacks.SetPictureCallback;
import com.bobpaulin.shuffletag.fragments.ProcessPictureFragment;
import com.bobpaulin.shuffletag.fragments.SetHostFragment;
import com.bobpaulin.shuffletag.fragments.TakePictureFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SetHostnameCallback, SetPictureCallback {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    
    String hostname;
    
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setHostname(String hostname) {
    	this.hostname = hostname;
    	
    }
    
    @Override
    public void setPicture(String path) {
    	this.picturePath = path;
    	
    }
    
    public String getPicturePath() {
		return picturePath;
	}
    
    public String getHostname() {
		return hostname;
	}

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        SetHostFragment hostFragment;
        
        TakePictureFragment takePictureFragement;
        
        ProcessPictureFragment processPictureFragment;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
        	switch(position) {
        		case 0:
        			hostFragment = SetHostFragment.newInstance(position + 1); 
        			return hostFragment;
        		case 1: 	
        			takePictureFragement = TakePictureFragment.newInstance(position + 1); 
        			return takePictureFragement;
        		case 2:
        			processPictureFragment = ProcessPictureFragment.newInstance(position + 1);
            		return processPictureFragment;
        	}
        	
        	return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

}
