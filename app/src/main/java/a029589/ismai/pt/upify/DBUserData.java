package a029589.ismai.pt.upify;

import android.icu.text.DisplayContext;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by Spanker Desktop on 19/05/2017.
 */

public class DBUserData extends AsyncTask<User,Void,User> {

    String useremail;

    public DBUserData(String useremail) {
        this.useremail = useremail;
    }

    @Override
    protected User doInBackground(User... users) {

        OkHttpClient client= new OkHttpClient();
        try {
           Request request = new Request.Builder()
                   .url("http://138.68.170.71:3000/users/"+useremail)
                   .build();

            Response response = client.newCall(request).execute();
            String result = response.body().string();


            Gson gson = new Gson();

            Type collectionType = new TypeToken<Collection<User>>() {}.getType();
            Collection<User> enums = gson.fromJson(result, collectionType);
            User[] userResult = enums.toArray(new User[enums.size()]);
            return  userResult[0];
        } catch (Exception e) {
            e.printStackTrace();
        }




        return null;
    }


}
