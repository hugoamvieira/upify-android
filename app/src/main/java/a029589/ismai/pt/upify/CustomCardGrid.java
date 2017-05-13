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
 * Created by Spanker Desktop on 13/05/2017.
 */

public class CustomCardGrid extends Card {


    protected TextView mTitle;
    protected Button mRate;
    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public CustomCardGrid(Context context) {
        this(context, R.layout.team_user_grid);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public CustomCardGrid(Context context, int innerLayout) {
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
        mRate = (Button) parent.findViewById(R.id.ratebutton);

        if (mTitle!=null)
            mTitle.setText("Zulmira");


        if (mRate!=null)
            mRate.setText("Rate Me");

    }
}
