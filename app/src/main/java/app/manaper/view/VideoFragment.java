package app.manaper.view;


 import android.graphics.Camera;
 import android.os.Bundle;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


 import androidx.databinding.DataBindingUtil;
import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.constantsutils.Codes;
 import app.manaper.databinding.FragmentVideoBinding;

public class VideoFragment extends BaseFragment {
    FragmentVideoBinding videoBinding;
    private Camera mCamera;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        videoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        videoBinding.playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovementManager.startActivity(getActivity(), Codes.PLAYING_VIDEO);
            }
        });


        return videoBinding.getRoot();
    }


}
