package a029589.ismai.pt.upify.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import a029589.ismai.pt.upify.CustomCard;
import a029589.ismai.pt.upify.DBReviewData;
import a029589.ismai.pt.upify.DBUserData;
import a029589.ismai.pt.upify.R;
import a029589.ismai.pt.upify.Review;
import a029589.ismai.pt.upify.User;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import it.gmariotti.cardslib.library.view.CardViewNative;

import static android.R.id.message;


public class FragmentOne extends Fragment implements ImagePickerCallback {


    public FragmentOne() {
        // Required empty public constructor
    }

    DBUserData dbuser;
    DBReviewData dbreview;
    ProgressBar experiencebar;
    RoundCornerProgressBar experiencebar_round;
    ProgressBar friendlybar;
    ProgressBar helpfulbar;
    ProgressBar socialbar;
    ImageView useravatarimage;
    RoundedImageView useravatarround;
    private String pickerPath;
    private int experiencebarProgressStatus = 0;
    private int remainingProgressStatus = 0;
    private int avatarLevelProgressStatus = 0;
    private Handler handlerexperiencebar = new Handler();
    private Handler handlerexperienceleft = new Handler();
    private Handler handleravatarlevel = new Handler();
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        String user_email = getActivity().getIntent().getExtras().getString("user_email");

        dbuser = new DBUserData(user_email);

       // experiencebar = (ProgressBar)view.findViewById(R.id.experiencebar);
          experiencebar_round = (RoundCornerProgressBar)view.findViewById(R.id.experiencebar_round);
        experiencebar_round.setMax(100);
        final TextView username = (TextView)view.findViewById(R.id.username);
        final TextView experienceleft = (TextView)view.findViewById(R.id.experienceleft);
        final TextView avatarLevel = (TextView)view.findViewById(R.id.avatarlevel);
        final TextView currentleveltextview = (TextView)view.findViewById(R.id.currentlevel);
        final TextView nextleveltextview = (TextView)view.findViewById(R.id.nextlevel);
        final TextView socialcounttextview = (TextView)view.findViewById(R.id.socialcounttext);
        final TextView helpfulcounttextview = (TextView)view.findViewById(R.id.helpfulcounttext);
        final TextView friendlycounttextview = (TextView)view.findViewById(R.id.friendlycounttext);

        useravatarimage= (ImageView) view.findViewById(R.id.avatar);
        useravatarround= (RoundedImageView) view.findViewById(R.id.avatarRound);
        Button btn25 = (Button)view.findViewById(R.id.button);
        Button btn100 = (Button)view.findViewById(R.id.button2);

        String  temp="";
        try {
            user = dbuser.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        NumberFormat format = new DecimalFormat("0.#");
        username.setText(user.getName());
        currentleveltextview.setText(format.format(user.getLevel())+"");
        nextleveltextview.setText(format.format(user.getLevel()+1)+"");
        socialcounttextview.setText(user.getOrange_medals()+"");
        friendlycounttextview.setText(user.getPurple_medals()+"");
        helpfulcounttextview.setText(user.getGreen_medals()+"");




        SetCircleBarOptions(view);




        //Thread for slow progress bar
        new Thread(new Runnable() {
            public void run() {

                Double currentexp =user.getExperience();
                Double totalxp= (user.getLevel()+1)*100;
                Double result = (currentexp/totalxp)*100;

                while (experiencebarProgressStatus < round(result,0)){
                    experiencebarProgressStatus += 1;
                    //Update progress bar with completion of operation
                    handlerexperiencebar.post(new Runnable() {
                        public void run() {

                            experiencebar_round.setProgress(experiencebarProgressStatus);

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

                Double currentexp =user.getExperience();
                final Double totalxp= (user.getLevel()+1)*100;

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
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //Thread for slow count of user level
        new Thread(new Runnable() {
            public void run() {

                //final Double totalxp= user.getLevel()*100;

                while (avatarLevelProgressStatus< user.getLevel()) {
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
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        dbreview = new DBReviewData(user.getId());

        ArrayList<Card> cards = new ArrayList<Card>();

        try {
            final  Review[]  reviews = dbreview.execute().get();

            for (Review r: reviews){
                Log.d(r.getGivenby_userid()+"",r.getStar_amount()+"");
                Card card = new CustomCard(getContext(),r);
                cards.add(card);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //ArrayList<Card> cards = new ArrayList<Card>();

        //Create a Card
       // Card card = new CustomCard(getContext());


        //Create a CardHeader
        //CardHeader header = new CardHeader(getContext());
        //Add Header to card
        //card.addCardHeader(header);
        //card.setTitle("hello fam");
/*
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);*/

        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);

        //Staggered grid view
        CardRecyclerView mRecyclerView = (CardRecyclerView)view.findViewById(R.id.carddemo_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }

        useravatarround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickImageSingle();
            }
        });
        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //experiencebar.setProgress(25);

                pickImageSingle();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(data);
            }
        }
    }

    private ImagePicker imagePicker;

    public void pickImageSingle() {
        imagePicker = new ImagePicker(this);
        imagePicker.shouldGenerateMetadata(true);
        imagePicker.shouldGenerateThumbnails(true);
        imagePicker.setImagePickerCallback(this);
        imagePicker.pickImage();
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


    @Override
    public void onImagesChosen(List<ChosenImage> list) {
        final  ChosenImage image = (ChosenImage) list.get(0);

        if (image.getThumbnailSmallPath() != null) {
            image.getThumbnailSmallPath();
           // Glide.with(getContext()).load(Uri.fromFile(new File(image.getThumbnailSmallPath()))).into(useravatarimage);
            useravatarround.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getContext()).load(Uri.fromFile(new File(image.getThumbnailSmallPath()))).into(useravatarround);
        }

    }

    @Override
    public void onError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // You have to save path in case your activity is killed.
        // In such a scenario, you will need to re-initialize the CameraImagePicker
        outState.putString("picker_path", pickerPath);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("picker_path")) {
                pickerPath = savedInstanceState.getString("picker_path");
            }
        }
    }

}