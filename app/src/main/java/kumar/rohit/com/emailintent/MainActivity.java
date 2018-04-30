package kumar.rohit.com.emailintent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mEmailButton;

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
    }

    // initView method definition
    private void initView() {
        mEmailButton = findViewById(R.id.button);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.button:
                // emailIntent method call
                emailIntent();


        }
    }

    // emailIntent method definition.
    private void emailIntent() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"vivek9075@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email Message");

        // Checking for app is available or not in the system for handle the email intent
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(emailIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        /*
         we can check the app in the system by usign these lines of code

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        */

        // Intent choose for selecting the app from the app list
        Intent chooser = Intent.createChooser(emailIntent, "Send Mail");
        // if App is available then start the intent
        if (isIntentSafe) {
            startActivity(chooser);
        }
    }
}
