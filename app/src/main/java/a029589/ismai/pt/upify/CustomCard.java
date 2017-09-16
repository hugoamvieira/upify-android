package a029589.ismai.pt.upify;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.ExecutionException;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Spanker Desktop on 12/05/2017.
 */

public class CustomCard  extends Card{


    protected TextView mTitle;
    protected TextView mSecondaryTitle;
    protected RatingBar mRatingBar;
    protected Review review;
    protected User given_by_user ;
    protected DBUserData dbuser;

    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public CustomCard(Context context, Review review) {
        this(context, R.layout.reviewpeerlayout);
        this.review = review;

    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public CustomCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    /**
     * Init
     */
    private void init(){

        //No Header

        //Set a OnClickListener listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click Listener card=", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.card_main_inner_simple_title);
       // mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
        mRatingBar = (RatingBar) parent.findViewById(R.id.ratingBar);
        Button hashtagfriendly = (Button)view.findViewById(R.id.hashtagfriendly);
        Button hashtagsocial = (Button)view.findViewById(R.id.hashtagsocial);
        Button hashtaghelpful = (Button)view.findViewById(R.id.hashtaghelpful);

        TextView username = (TextView) parent.findViewById(R.id.card_main_inner_simple_title);
        TextView userlevel = (TextView) parent.findViewById(R.id.userlevel);

        NumberFormat format = new DecimalFormat("0.#");

        //Retrieve User info
        dbuser=new DBUserData(review.getGivenby_userid()+"");

        try {
            given_by_user = dbuser.execute().get();
            username.setText(given_by_user.getName());

            userlevel.setText(format.format(given_by_user.getLevel())+"");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        if (hashtagfriendly!=null)
            //hashtagbutton.setText("#friendly");
        //Friendly Button
        if ( review.getHashtag_id()==1){

            hashtagfriendly.setText("#friendly");
           // hashtagbutton.setBackgroundColor(Color.parseColor("#ff9e30"));
            hashtagfriendly.setVisibility(View.VISIBLE);
            hashtagsocial.setVisibility(View.GONE);
            hashtaghelpful.setVisibility(View.GONE);
        }
        //Social Button
        if ( review.getHashtag_id()==2){

            hashtagsocial.setText("#social");
            hashtagfriendly.setVisibility(View.GONE);
            hashtagsocial.setVisibility(View.VISIBLE);
            hashtaghelpful.setVisibility(View.GONE);
        }
        //Helpful Button
        if ( review.getHashtag_id()==3){


            hashtaghelpful.setText("#helpful");
            hashtagfriendly.setVisibility(View.GONE);
            hashtagsocial.setVisibility(View.GONE);
            hashtaghelpful.setVisibility(View.VISIBLE);
        }
            //hashtagbutton.setText(review.getHashtag_id()+"");

        if (mTitle!=null){
            // mTitle.setText("Adalberto Parede");
            mTitle.setText(review.getGivenby_userid()+"");
        }



        if (mRatingBar!=null){
            mRatingBar.setNumStars(5);
            mRatingBar.setMax(5);
            mRatingBar.setRating(review.getStar_amount());

        }

    }
}
