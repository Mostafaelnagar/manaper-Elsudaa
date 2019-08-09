package app.manaper;


import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import app.manaper.base.BaseFragment;
import app.manaper.databinding.FragmentPlayingBinding;


public class Playing extends BaseFragment {

    FragmentPlayingBinding playingBinding;

    public Playing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        playingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_playing, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA}, 1);
                initVideo();
            } else {
                // start picker to get image for cropping and then use the image in cropping activity
                initVideo();
            }
        } else {
            initVideo();
        }
        return playingBinding.getRoot();
    }

    private void initVideo() {
        try {
            accessLoadingBar(View.VISIBLE);
            playingBinding.videoPlayer.setVideoPath("https://mnaber.my-staff.net/mnaberfiles/resources/assets/img/settings/original/video.mp4");
            MediaController mediaController = new
                    MediaController(getActivity());
            mediaController.setAnchorView(playingBinding.videoPlayer);
            playingBinding.videoPlayer.setMediaController(mediaController);
            playingBinding.videoPlayer.requestFocus();
            playingBinding.videoPlayer.start();
            playingBinding.videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    accessLoadingBar(View.GONE);
                }
            });
        } catch (Exception e) {
            Log.i("initVideo", "initVideo: " + e.getMessage());
            Toast.makeText(getActivity(), "Error playing video", Toast.LENGTH_SHORT).show();
        }


    }

}
