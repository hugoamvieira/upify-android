package a029589.ismai.pt.upify.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import a029589.ismai.pt.upify.R;

import static a029589.ismai.pt.upify.R.id.textView;


public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }

    ProgressBar levelbar;
    ProgressBar friendlybar;
    ProgressBar helpfulbar;
    ProgressBar socialbar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

         levelbar = (ProgressBar)view.findViewById(R.id.levelbar);
        Button btn25 = (Button)view.findViewById(R.id.button);
        Button btn100 = (Button)view.findViewById(R.id.button2);


        SetCircleBarOptions(view);

        //Long operation by thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 40) {
                    progressStatus += 1;
                    //Update progress bar with completion of operation
                    handler.post(new Runnable() {
                        public void run() {
                            levelbar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelbar.setProgress(25);
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelbar.setProgress(100);
            }
        });
        return  view;
    }


    public  void SetCircleBarOptions(View view){
        CircularProgressBar circularFriendlyBar = (CircularProgressBar)view.findViewById(R.id.friendlybar);
        CircularProgressBar circularSocialBar = (CircularProgressBar)view.findViewById(R.id.socialbar);
        CircularProgressBar circularHelpfulBar = (CircularProgressBar)view.findViewById(R.id.helpfulbar);
        //circularProgressBar.setColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
        //circularProgressBar.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.primary_light));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularFriendlyBar.setProgressWithAnimation(70, animationDuration); // Default duration = 1500ms
        circularSocialBar.setProgressWithAnimation(25, animationDuration);
        circularHelpfulBar.setProgressWithAnimation(55, animationDuration);

    }

}