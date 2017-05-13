package a029589.ismai.pt.upify.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import a029589.ismai.pt.upify.CustomCard;
import a029589.ismai.pt.upify.R;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import it.gmariotti.cardslib.library.view.CardViewNative;


public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }

    ProgressBar experiencebar;
    ProgressBar friendlybar;
    ProgressBar helpfulbar;
    ProgressBar socialbar;
    private int experiencebarProgressStatus = 0;
    private int remainingProgressStatus = 0;
    private int avatarLevelProgressStatus = 0;
    private Handler handlerexperiencebar = new Handler();
    private Handler handlerexperienceleft = new Handler();
    private Handler handleravatarlevel = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        experiencebar = (ProgressBar)view.findViewById(R.id.experiencebar);
        final TextView experienceleft = (TextView)view.findViewById(R.id.experienceleft);
        final TextView avatarLevel = (TextView)view.findViewById(R.id.avatarlevel);
        Button btn25 = (Button)view.findViewById(R.id.button);
        Button btn100 = (Button)view.findViewById(R.id.button2);


        SetCircleBarOptions(view);

        //Thread for slow progress bar
        new Thread(new Runnable() {
            public void run() {

                Double currentexp =600.0;
                Double totalxp= 2100.0;
                Double result = (currentexp/totalxp)*100;

                while (experiencebarProgressStatus < round(result,0)){
                    experiencebarProgressStatus += 1;
                    //Update progress bar with completion of operation
                    handlerexperiencebar.post(new Runnable() {
                        public void run() {
                            experiencebar.setProgress(experiencebarProgressStatus);
                           // experienceleft.setText((experiencebarProgressStatus*10)+"/"+"2100 XP");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //Thread for slow count of XP
        new Thread(new Runnable() {
            public void run() {

                Integer currentexp =600;
                final Integer totalxp= 2100;

                while (remainingProgressStatus< currentexp) {
                    remainingProgressStatus += 20;
                    //Update progress bar with completion of operation
                    handlerexperienceleft.post(new Runnable() {
                        public void run() {
                            experienceleft.setText(remainingProgressStatus+"/"+totalxp+" XP");
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

        //Thread for slow count of user level
        new Thread(new Runnable() {
            public void run() {

                final Integer currentLevel =20;

                while (avatarLevelProgressStatus< currentLevel) {
                    avatarLevelProgressStatus += 1;
                    //Update progress bar with completion of operation
                    handleravatarlevel.post(new Runnable() {
                        public void run() {
                            avatarLevel.setText(avatarLevelProgressStatus+"");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



        ArrayList<Card> cards = new ArrayList<Card>();

        //Create a Card
        Card card = new CustomCard(getContext());


        //Create a CardHeader
        //CardHeader header = new CardHeader(getContext());
        //Add Header to card
        //card.addCardHeader(header);
        //card.setTitle("hello fam");

        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);

        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);

        //Staggered grid view
        CardRecyclerView mRecyclerView = (CardRecyclerView)view.findViewById(R.id.carddemo_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }


        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experiencebar.setProgress(25);
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experiencebar.setProgress(100);
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
        else {
        }
    }

}