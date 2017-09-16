package a029589.ismai.pt.upify;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.PopupWindow;
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
    private  CheckedTextView socialCheckedTextPop;
    private CheckBox friendlyCheckBoxPop;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
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
    public void setupInnerViewElements(final ViewGroup parent, View view) {

        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.card_main_inner_simple_title);
        mRate = (Button) parent.findViewById(R.id.ratebutton);

        mRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "hey", Toast.LENGTH_LONG).show();
                layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup)layoutInflater.inflate(R.layout.rate_popup,null);
                //container.setAlpha(0.8f);
                container.findViewById(R.id.backgroundpop).setAlpha(0.8f);
                //socialCheckedTextPop =(CheckedTextView)container.findViewById(R.id.so);
                friendlyCheckBoxPop = (CheckBox)container.findViewById(R.id.friendlycheckBox);

                friendlyCheckBoxPop.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public  void onClick(View view ){






                    }
                });

               /* socialCheckedTextPop.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public  void onClick(View view ){
                        if ( socialCheckedTextPop.isChecked()){
                            socialCheckedTextPop.setChecked(false);
                        }else{
                            socialCheckedTextPop.setChecked(true);
                        }

                    }
                });*/
                popupWindow = new PopupWindow(container, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            }
        });

        if (mTitle!=null)
            mTitle.setText("Zulmira");


        if (mRate!=null)
            mRate.setText("Rate Me");

    }
}
