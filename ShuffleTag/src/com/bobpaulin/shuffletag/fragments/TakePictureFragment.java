package com.bobpaulin.shuffletag.fragments;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bobpaulin.shuffletag.R;
import com.bobpaulin.shuffletag.callbacks.SetPictureCallback;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TakePictureFragment extends Fragment {
	/**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    static final int REQUEST_TAKE_PHOTO = 1;
    
    private String mCurrentPhotoPath;
    
    private SetPictureCallback pictureCallback;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TakePictureFragment newInstance(int sectionNumber) {
    	TakePictureFragment fragment = new TakePictureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TakePictureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.takepicture_fragment_main, container, false);
        Button takePictureButton = (Button)rootView.findViewById(R.id.takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dispatchTakePictureIntent();
			}
		});
        return rootView;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        pictureCallback.setPicture(mCurrentPhotoPath);
        return image;
    }

    private void dispatchTakePictureIntent() {
    	Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
    	
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }

    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            pictureCallback = (SetPictureCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement SetPictureCallback");
        }
    }

    
    public String getCurrentPhotoPath() {
		return mCurrentPhotoPath;
	}

}
