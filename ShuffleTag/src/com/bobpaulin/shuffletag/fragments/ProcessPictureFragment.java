package com.bobpaulin.shuffletag.fragments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;

import com.bobpaulin.shuffletag.MainActivity;
import com.bobpaulin.shuffletag.R;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProcessPictureFragment extends Fragment {
	/**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    private String hostName;
    
    private String pictureName;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProcessPictureFragment newInstance(int sectionNumber) {
    	ProcessPictureFragment fragment = new ProcessPictureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ProcessPictureFragment() {
    }
    


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.processpicture_fragment_main, container, false);
        
        Button processPictureButton = (Button)rootView.findViewById(R.id.procesPictureButton);
        processPictureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity mainActivity = (MainActivity)getActivity();
				new UploadPictureTask().execute(mainActivity.getPicturePath(), mainActivity.getHostname());
				
			}
		});
        return rootView;
    }
    
    private static class UploadPictureTask extends AsyncTask<String, Void, String> {
    	
    	@Override
    	protected String doInBackground(String... params) {
    		System.out.println(params[0] + " " + params[1]);
    		
    		AndroidHttpClient httpClient = AndroidHttpClient.newInstance("Android");
			HttpPost postRequest = new HttpPost("http://" + params[1] + "/test");
			
			try {
				InputStreamEntity pictureEntity = new InputStreamEntity(new FileInputStream(new File(params[0].replace("file:", ""))), -1);
				pictureEntity.setContentType("binary/octet-stream");
				pictureEntity.setChunked(true); // Send in multiple parts if needed
				postRequest.setEntity(pictureEntity);
			    HttpResponse response = httpClient.execute(postRequest);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
    }
}
