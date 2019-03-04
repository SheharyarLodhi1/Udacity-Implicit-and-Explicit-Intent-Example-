package com.next.sheharyar.explicitandimplicitintent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String TEXT_HOLDER_FOR_URL;
    private EditText mNameEntry;
    private Button mDoSomethingCoolButton, mOpenWebBrowser, mOpenMap, mShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEntry = (EditText)findViewById(R.id.et_send_data);
        mDoSomethingCoolButton = (Button)findViewById(R.id.bt_send_data);
        mOpenWebBrowser = (Button)findViewById(R.id.bt_open_web_browser);
        mOpenMap = (Button)findViewById(R.id.bt_open_map);
        mShareButton = (Button)findViewById(R.id.bt_share_app);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // COMPLETED (5) Specify a String you'd like to share
        /* Create the String that you want to share */
                String textThatYouWantToShare =
                        "Sharing the coolest thing I've learned so far. You should " +
                                "check out Udacity and Google's Android Nanodegree!";

                // COMPLETED (6) Replace the Toast with shareText, passing in the String from step 5
        /* Send that text to our method that will share it. */
                methodToShareApp(textThatYouWantToShare);
//                String mimeType = "text/plain";
//                String title = "Learning how to share";
//                String textToShare = "Hello There ..?";
//
//                ShareCompat.IntentBuilder.from()
//                        .setChooserTitle(title)
//                        .setType(mimeType)
//                        .setText(textToShare);
            }
        });
        mOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addressString = "i9/3 Evamp and Sanga, Islamabad";
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo")
                        .path("0,0")
                        .appendQueryParameter("q",addressString);
                Uri addressUri = builder.build();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(addressUri);
                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
                openMap();
            }
        });
        mDoSomethingCoolButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String textEntered = mNameEntry.getText().toString();
                TEXT_HOLDER_FOR_URL = textEntered;
                Intent startChildActivity  = new Intent(getApplicationContext(), ChildActivity.class);

                startChildActivity.putExtra(Intent.EXTRA_TEXT, textEntered);
                startActivity(startChildActivity);
            }
        });

        mOpenWebBrowser.setOnClickListener(new View.OnClickListener() {

//            String newUrlAddress = TEXT_HOLDER_FOR_URL;
            String urlAddress = "https://www.google.com.pk";
            @Override
            public void onClick(View view) {
                openWebPage(urlAddress);
            }
        });
    }

    private void methodToShareApp(String textToShare) {

        // COMPLETED (2) Create a String variable called mimeType and set it to "text/plain"
        /*
         * You can think of MIME types similarly to file extensions. They aren't the exact same,
         * but MIME types help a computer determine which applications can open which content. For
         * example, if you double click on a .pdf file, you will be presented with a list of
         * programs that can open PDFs. Specifying the MIME type as text/plain has a similar affect
         * on our implicit Intent. With text/plain specified, all apps that can handle text content
         * in some way will be offered when we call startActivity on this particular Intent.
         */
        String mimeType = "text/plain";

        // COMPLETED (3) Create a title for the chooser window that will pop up
        /* This is just the title of the window that will pop up when we call startActivity */
        String title = "Learning How to Share";

        // COMPLETED (4) Use ShareCompat.IntentBuilder to build the Intent and start the chooser
        /* ShareCompat.IntentBuilder provides a fluent API for creating Intents */
        ShareCompat.IntentBuilder
                /* The from method specifies the Context from which this share is coming from */
                .from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }

    private void openMap() {

    }

    private void openWebPage(String url) {

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
