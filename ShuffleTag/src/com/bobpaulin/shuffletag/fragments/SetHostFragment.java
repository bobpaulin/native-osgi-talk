package com.bobpaulin.shuffletag.fragments;

import com.bobpaulin.shuffletag.R;
import com.bobpaulin.shuffletag.callbacks.SetHostnameCallback;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class SetHostFragment extends Fragment {

	/**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    private String hostName;
    
    private SetHostnameCallback hostNameListener;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SetHostFragment newInstance(int sectionNumber) {
    	SetHostFragment fragment = new SetHostFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SetHostFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sethost_fragment_main, container, false);
        final EditText hostNameEditText = (EditText) rootView.findViewById(R.id.hostNameTextBox);
        hostNameEditText.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				hostNameListener.setHostname(hostNameEditText.getText().toString());
				return false;
			}
		});
        return rootView;
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            hostNameListener = (SetHostnameCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement SetHostnameCallback");
        }
    }

    
    public String getHostName() 
    {
		return hostName;
	}
}
