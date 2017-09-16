package a029589.ismai.pt.upify;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by Spanker Desktop on 21/05/2017.
 */

public class DBReviewData extends AsyncTask<Review[],Void,Review[]> {

    Integer userid;

    public DBReviewData(Integer userid) {
        this.userid = userid;
    }



    @Override
    protected Review[] doInBackground(Review[]... reviews) {

        OkHttpClient client= new OkHttpClient();
        try {
            Request request = new Request.Builder()
                                      .url("http://138.68.170.71:3000/users/"+userid+"/reviews")
                                      .build();

            Response response = client.newCall(request).execute();
            String result = response.body().string();


            Gson gson = new Gson();

            Type collectionType = new TypeToken<Collection<Review>>() {}.getType();
            Collection<Review> enums = gson.fromJson(result, collectionType);
            Review[] reviewResult = enums.toArray(new Review[enums.size()]);
            return  reviewResult;
        } catch (Exception e) {
            e.printStackTrace();
        }




        return null;
    }


}
