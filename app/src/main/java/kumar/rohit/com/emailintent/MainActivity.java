package kumar.rohit.com.emailintent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button mEmailButton, mCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initView method call
        initView();

        // registerListener method call
        registerListener();

    }

    //registerListner method definition
    private void registerListener() {

        mEmailButton.setOnClickListener(this);
        mCallButton.setOnClickListener(this);
    }

    // initView method definition
    private void initView() {

        mEmailButton = findViewById(R.id.email_button);
        mCallButton = findViewById(R.id.call_button);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.email_button:

                Log.d(TAG, "onClick: Email Button Clicked");
                // emailIntent method call
                emailIntent();
                break;

            case R.id.call_button:

                Log.d(TAG, "onClick: Call button Clicked");

                // callIntent method call
                callIntent();


        }
    }

    // CallIntent method definition
    private void callIntent() {

        Log.d(TAG, "callIntent: Method Called");

        String phone = "198";
        Intent mCallIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));

        // Checking app availability for handle the intent
        boolean isIntentSafe = isIntentSafe(mCallIntent);

        // Intent choose for selecting the app from the app list
        Intent chooser = Intent.createChooser(mCallIntent, "Select App For call");
        // if App is available then start the intent
        if (isIntentSafe) {
            startActivity(chooser);
        }
    }

    private boolean isIntentSafe(Intent mCallIntent) {

        Log.d(TAG, "isIntentSafe: Method Called");
        // Checking for app is available or not in the system for handle the email intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mCallIntent, 0);
        return activities.size() > 0;

        /*
         we can check the app in the system by usign these lines of code

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        */
    }


    // emailIntent method definition.
    private void emailIntent() {

        Log.d(TAG, "emailIntent: Method Called");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"vivek9075@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email Message");

        // Checking app availability to handle the intent
        boolean isIntentSafe = isIntentSafe(emailIntent);

        // Intent choose for selecting the app from the app list
        Intent chooser = Intent.createChooser(emailIntent, "Send Mail");
        // if App is available then start the intent
        if (isIntentSafe) {
            startActivity(chooser);
        }
    }
}
