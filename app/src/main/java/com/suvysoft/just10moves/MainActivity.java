package com.suvysoft.just10moves;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;
    private TextView textView, resText, moves;
    private Button higher,lower,yes,ok, dis1, dis2;
    private int tries = 0, left = 1, right = 1000, mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
// Call displayInterstitial() function
                displayInterstitial();
            }
        });

        textView = (TextView) findViewById(R.id.textView);
        moves = (TextView) findViewById(R.id.moves);
        resText = (TextView) findViewById(R.id.resText);
        higher = (Button) findViewById(R.id.higher);
        lower = (Button) findViewById(R.id.lower);
        yes = (Button) findViewById(R.id.yes);
        ok = (Button) findViewById(R.id.ok);
        dis1 = (Button) findViewById(R.id.dis1);
        dis2 = (Button) findViewById(R.id.dis2);

        higher.setVisibility(View.INVISIBLE);
        dis1.setVisibility(View.INVISIBLE);
        dis2.setVisibility(View.INVISIBLE);
        lower.setVisibility(View.INVISIBLE);
        yes.setVisibility(View.INVISIBLE);
        moves.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        resText.setVisibility(View.VISIBLE);

        resText.setText("Think of a number between"+'\n'+
                "1 and 1000"+'\n'+"I will guess it within 10 Moves");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    guess();
                    higher.setVisibility(View.VISIBLE);
                    lower.setVisibility(View.VISIBLE);
                    yes.setVisibility(View.VISIBLE);
                    moves.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.INVISIBLE);
                    dis1.setVisibility(View.INVISIBLE);
                    dis2.setVisibility(View.INVISIBLE);
                    resText.setVisibility(View.INVISIBLE);



            }

        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resText.setText("I Guessed Your Number in :"+'\n' +tries +" MOVES");
                higher.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                lower.setVisibility(View.INVISIBLE);
                dis1.setVisibility(View.INVISIBLE);
                dis2.setVisibility(View.INVISIBLE);
                yes.setVisibility(View.INVISIBLE);
                moves.setVisibility(View.INVISIBLE);
                ok.setVisibility(View.VISIBLE);
                resText.setVisibility(View.VISIBLE);

               textView.setText("  "+"Number is "+mid);
                ok.setText("Try Again!");
                tries = 0;
                left = 1;
                right = 1000;

            }
        });
        higher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left = mid + 1;
                guess();

            }
        });
        lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right = mid - 1;
                guess();

            }
        });


    }

    private  void guess(){
        mid = (left + right) / 2;
        tries++;
        textView.setText("Is your number " +" "+"  "+ mid+"?");
        if(tries < 10)
        {
            moves.setText("MOVES : "+ tries);
        }
        else
        {
            tries = 10;
            dis1.setVisibility(View.VISIBLE);
            dis2.setVisibility(View.VISIBLE);
            moves.setText("MOVES : "+ tries);
        }


    }


    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
