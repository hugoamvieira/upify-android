package a029589.ismai.pt.upify;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Upify";

    private ProgressDialog progress;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind your views
        final EditText emailEditText = (EditText) findViewById(R.id.emailEditext);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditext);
        Button dbLoginButton = (Button) findViewById(R.id.dbLoginButton);
        Button webLoginButton = (Button) findViewById(R.id.webLoginButton);

        // Add the onClick listener to the database login
        dbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show a progress dialog to block the UI while the request is being made.
                login(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
        // Add the onClick listener to the web auth login
        webLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show a progress dialog to block the UI while the request is being made.
                loginGoogle();
            }
        });
    }


    private void login(String email, String password) {
        Auth0 auth0 = new Auth0(context);
        AuthenticationAPIClient client = new AuthenticationAPIClient(auth0);

        progress = ProgressDialog.show(this, null, "Logging in..", true, false);
        progress.show();

        String connectionName = "UpifyDatabase";
        client.login(email, password, connectionName)
                .start(new BaseCallback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials payload) {
                        progress.dismiss();
                        changeActivity(context, MainActivity.class);
                        finish();
                    }

                    @Override
                    public void onFailure(final AuthenticationException error) {
                        progress.dismiss();
                        //Show error to the user
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }


    private void loginGoogle() {
        Auth0 acc = new Auth0(context);

        final AuthCallback callback = new AuthCallback() {
            @Override
            public void onFailure(@NonNull Dialog dialog) {
                Log.d(TAG, "Unknown Error");
                dialog.show();
            }

            @Override
            public void onFailure(AuthenticationException exception) {
                Log.d(TAG, "Auth Error");
            }

            @Override
            public void onSuccess(@NonNull Credentials credentials) {
                Log.d(TAG, "Succes" + credentials.getAccessToken());
                changeActivity(context, MainActivity.class);
            }
        };

        WebAuthProvider.init(acc)
                .withConnection("google-oauth2")
                .start(LoginActivity.this, callback);
    }


    private void changeActivity(Context ctx, Class<?> cls) {
        Intent i = new Intent(ctx, cls);
        startActivity(i);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        //Check if the result belongs to a pending web authentication
        if (WebAuthProvider.resume(intent)) {
            return;
        }
        super.onNewIntent(intent);
    }
}