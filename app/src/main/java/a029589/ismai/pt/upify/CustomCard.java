package a029589.ismai.pt.upify;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Spanker Desktop on 12/05/2017.
 */

public class CustomCard  extends Card{


    protected TextView mTitle;
    protected TextView mSecondaryTitle;
    protected RatingBar mRatingBar;

    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public CustomCard(Context context) {
        this(context, R.layout.reviewpeerlayout);
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
        Button hashtagbutton = (Button)view.findViewById(R.id.hashtagbutton);

        if (hashtagbutton!=null)
            hashtagbutton.setText("#friendly");

        if (mTitle!=null)
            mTitle.setText("Adalberto Parede");


        if (mRatingBar!=null)
            mRatingBar.setNumStars(5);
        mRatingBar.setMax(5);
        mRatingBar.setRating(4);

    }
}
