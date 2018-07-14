package com.mozo.meyaz.colicbaby;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
/*import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;*/
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;



import com.facebook.ads.*;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onesignal.OneSignal;

import java.util.Calendar;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import io.fabric.sdk.android.Fabric;

import static android.view.View.VISIBLE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.getInstance;




public class MainActivity extends AppCompatActivity {


    private static final String FORMAT = "%02d:%02d:%02d";
    //  private static int reklamSure = 3000;
    //   public ImageView reklam;
    //  public static AdView mAdView;
    // MediaPlayer ve sonSes tanımlamaları----------------
    public MediaPlayer mp;
    public int sonSes, yedekSes;
    // private AdView adView;  // FaceBook Add Service
    //-----------------------------------------------------
    // -----  SoundPool Tanımlamaları
    public SoundPool soundPool;
    public int streamId, music1, music2, music3, music4, music5, music6, music7, music8, music9, music10, music11, musicSessiz, musicCode;
    public boolean sesCaliyormu = false;
    // -----  Layout Tanımlaması
    public RelativeLayout appEkrani, splashEkrani;
    public int simdikiSaat, simdikiDakika;
    public CountDownTimer sayici;


    //  Notification tanımlamaları---------------
/*    private static final int NOTIFICATION_EX = 1;
    private NotificationManager notificationManager;*/
    //-------------------------------------------------
    public boolean iptalKontrol = true;
    public boolean iptalbtn = false;
    public boolean cagriBittimi = false;
    public TextView sayiciText;
    public ImageButton supurge_buton, fon_buton, anneKarni_buton, camasir_buton, araba_buton, tren_buton,
            fan_buton, kalp_buton, yagmur_buton, white_buton, musicbox_buton, banyo_buton,
            kapatButon, iptalButon;
    public ImageView appBarLogoImage;
    //--------------------------------
    //private AdView mAdView;  //  Admob add Service
    private FirebaseAnalytics mFirebaseAnalytics;

    private AdView adView;  // Facebook Ad Service
  //  private InterstitialAd interstitialAd;  // Facebook Intersitial Service
  private InterstitialAd mInterstitialAd;     // Admob Intersitial Service



//    public View viewLayout;  // splash View
//private static boolean splashLoaded = false;
//private static int SPLASH_TIME_OUT = 4000;

    // public int userid =1000;

// ----------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        MobileAds.initialize(this, "ca-app-pub-3039080113363361~7767057930");            // Gerçek id
      //  MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");   // Test id

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3039080113363361/4474195155");           //  Gerçek reklam birimi
      //  mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");              //  Test birimi

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("585E4A0D84C6B03D5404298D8AEA4C76")
                .build();
        mInterstitialAd.loadAd(adRequest);


        //mInterstitialAd.loadAd(new AdRequest.Builder().build());



// Initialize the Mobile Ads SDK.

/*
        MobileAds.initialize(this, "ca-app-pub-3039080113363361~7767057930");


        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = findViewById(R.id.adView);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);



//  ----------------adView Olay dinleyicileri ------------
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed");
            }
        });


        */


//---------------  FACEBOOK Reklam Kodları ----------------------------

// Instantiate an AdView object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        adView = new AdView(this, "951265818413528_951267748413335", AdSize.BANNER_HEIGHT_50);

        // Instantiate an InterstitialAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).


        //interstitialAd = new InterstitialAd(this, "951265818413528_951425435064233");


        // Find the Ad Container
        LinearLayout adContainer =  findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);


        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
              //  Toast.makeText(MainActivity.this, "Error: " + adError.getErrorMessage() + " - " + adError.getErrorCode(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
                Log.i("Ads", "Ad Loaded");

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.i("Ads", "Ad Clicked");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.i("Ads", "Logging Impression");
            }
        });



        // Request an ad
       // AdSettings.addTestDevice("HASHED ID");
        adView.loadAd();


        /*

        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("interstitialAd", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("interstitialAd", "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("interstitialAd", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("interstitialAd", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("interstitialAd", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("interstitialAd", "Interstitial ad impression logged!");
            }
        });

*/
//--------------------------------------------------------------------

        //Analiz öğeleri ---

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //---------------------


        //  FAcebook Analiz ----------------------
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        AppEventsLogger logger = AppEventsLogger.newLogger(this);


        /**
         * This function assumes logger is an instance of AppEventsLogger and has been
         * created using AppEventsLogger.newLogger() call.
         */

        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        //----------------------------------------------------------

        //--- Toast Splash----------
/*
        LayoutInflater layoutInflater=getLayoutInflater();
        viewLayout=layoutInflater.inflate(R.layout.splash2,
                (ViewGroup)findViewById(R.id.splashLayout));

        SplashToastGoster();
*/
//  --------------  OneSignal Entegrasyonu  --------

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

// -----------------------------------------------------

        //---------------------------  status bar renk -------------------------

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        //-----------------------------------------------------------------------


        mp = MediaPlayer.create(this, R.raw.musicbox);// MediaPlayer tanımlamaları
        sonSes = 0;


        // SoundPool Tanımlaması
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);


        music1 = soundPool.load(this, R.raw.supurge, 1);
        music2 = soundPool.load(this, R.raw.fon, 1);
        music3 = soundPool.load(this, R.raw.camasir, 1);
        music4 = soundPool.load(this, R.raw.kalp, 1);
        music5 = soundPool.load(this, R.raw.annekarni, 1);
        music6 = soundPool.load(this, R.raw.fan, 1);
        music7 = soundPool.load(this, R.raw.araba, 1);
        music8 = soundPool.load(this, R.raw.dus, 1);
        music9 = soundPool.load(this, R.raw.yagmur, 1);
        music10 = soundPool.load(this, R.raw.beyazses, 1);
        music11 = soundPool.load(this, R.raw.tren, 1);
        musicSessiz = soundPool.load(this, R.raw.sessiz, 1);


//------------------------------------------------------

        appEkrani = findViewById(R.id.appEkrani);
        splashEkrani = findViewById(R.id.splashEkrani);

        splashGecis();

        // ADMOB Entegrasyonu---------------------------------
        /*MobileAds.initialize(getApplicationContext(), "ca-app-pub-3039080113363361~7767057930");



        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed");
            }
        });*/

        // ---------------------------------------------------------------

       /* if (!splashLoaded) {
            setContentView(R.layout.splash);
            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(splash.this, MainActivity.class));
                    finish();
                }
            }, secondsDelayed * 500);

            splashLoaded = true;
        }
        else {
            Intent goToMainActivity = new Intent(MainActivity.this, MainActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
*/
       /* if (splashCalismadi == true)
        {
            splashCalismadi=false;

            Intent i = new Intent(MainActivity.this, splash2.class);
            startActivity(i);
        }
        else
        {

        }*/


//-----------------------------------------------------------------------

//        FacebookSdk.sdkInitialize(getApplicationContext());     // Facebook analtcs
//        AppEventsLogger.activateApp(this);                      // bağlantısı


        // ADMOB Entegrasyonu---------------------------------
     /*   MobileAds.initialize(getApplicationContext(), "ca-app-pub-4061139306454785~5843093556");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/


        //----------------------------- FaceBook Add Integration -----------------------------

//        RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);

//        adView = new AdView(this, "914735072003124_914739682002663", AdSize.BANNER_320_50);

//        adViewContainer.addView(adView);

       /* adView.setAdListener(new AdListener() {
                                 @Override
                                 public void onError(Ad ad, AdError adError) {
                                     // Ad error callback
                                     Toast.makeText(MainActivity.this, "Error: " + adError.getErrorMessage(),
                                             Toast.LENGTH_LONG).show();
                                 }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });


        // AdSettings.addTestDevice("2936098fa5edad349b73e227981cee1b");
        AdSettings.addTestDevice("d46b7a1bae6b08dd64066ab5f431a217");*/
//        adView.loadAd();


        //------------------------------------- Fabrik Analiz Kurulum------------------
        Fabric.with(this, new Crashlytics());
        // TODO: Move this to where you establish a user session


        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        // --------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------


        // Rate App Entegrasyonu  ---------------------------------------------------------

        AppRate.with(this)
                .setInstallDays(10) // default 10, 0 means install day.
                .setLaunchTimes(10) // default 10
                .setRemindInterval(1) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
        //------------------------------------------------------------------------

        // Notification Entegrasyonu -------------------------------------------------

        // servisCalistir();

        //-------------------------------------------------------------------------------


        //----------  Buton tanımlamaları ---------------------------------------
        //    final ImageButton supurge_buton, fon_buton, anneKarni_buton, camasir_buton, araba_buton, tren_buton;

        supurge_buton =  findViewById(R.id.supurge_btn);
        fon_buton =  findViewById(R.id.fon_btn);
        anneKarni_buton =  findViewById(R.id.annekarni_btn);
        camasir_buton =  findViewById(R.id.camasir_btn);
        araba_buton =  findViewById(R.id.araba_btn);
        banyo_buton =  findViewById(R.id.banyo_btn);
        tren_buton = findViewById(R.id.tren_btn);
        musicbox_buton = findViewById(R.id.musicbox_btn);
        white_buton =  findViewById(R.id.white_btn);
        kalp_buton = findViewById(R.id.kalp_btn);
        yagmur_buton =  findViewById(R.id.yagmur_btn);
        fan_buton =  findViewById(R.id.fan_btn);
        kapatButon =  findViewById(R.id.kapatButon);
        iptalButon =  findViewById(R.id.iptalButon);
//---------------------------------------------------------------------------


// -------------------------------------------------------------------------

        sayiciText =  findViewById(R.id.kolik);
        appBarLogoImage =  findViewById(R.id.appBarLogoImage);

//-----------------------------------------------------------------------

        //   ses durdurma Özelliği

        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    //INCOMING call
                    //do all necessary action to pause the audio
                    //   if (mp != null) {//check mp
                    // setPlayerButton(true, false, true);

                       /* if (mp.isPlaying()) {

                            mp.pause();
                            cagriBittimi = true;
                        }
                        else
                        {
                            soundPool.stop(streamId);
                            cagriBittimi = true;

                        }*/
                    //sesleriSustur();
                    //   }
                    yedekSes = sonSes;

                    cagriSesKontrol();
                    //sesleriSustur();


                } else if (state == TelephonyManager.CALL_STATE_IDLE) {

//                    sayiciText.setText(yedekSes+"");
                    Log.v("yedekSes:  ", yedekSes + "");
                    //Not IN CALL
                    //do anything if the phone-state is idle
                  /*  if (cagriBittimi) {
                        if (mp != null)
                        {
                            mp.start();
                            cagriBittimi = false;
                        }
                        else
                        {
                            streamId = soundPool.play(musicCode,1,1,0,-1,1);
                            cagriBittimi = false;

                        }

                    }*/

                    switch (yedekSes) {
                        case 1:
                            sesCal(1, music1);
                            break;
                        case 2:
                            sesCal(2, music2);
                            break;
                        case 3:
                            sesCal(3, music3);
                            break;
                        case 4:
                            sesCal(4, music4);
                            break;
                        case 5:
                            sesCal(5, music5);
                            break;
                        case 6:
                            sesCal(6, music6);
                            break;
                        case 7:
                            sesCal(7, music7);
                            break;
                        case 8:
                            sesCal(8, music8);
                            break;
                        case 9:
                            sesCal(9, music9);
                            break;
                        case 10:
                            sesCal(10, music10);
                            break;
                        case 11:
                            sesCal(11, music11);
                            break;
                        case 12:
                            sesCal(12, 0);
                            break;


                    }
                    //
                } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    //A call is dialing, active or on hold
                    //do all necessary action to pause the audio
                    //do something here
             /*       if (mp != null) {//check mp
                        //  setPlayerButton(true, false, true);

                        if (mp.isPlaying()) {

                            mp.pause();
                        }
                    }
                    else
                    {
                        soundPool.stop(streamId);  //
                        cagriBittimi = true;        // TODO :Bİr kitlenmede burada oluyor


                    }
*/
                    yedekSes = sonSes;
                    cagriSesKontrol();

                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };//end PhoneStateListener

        TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (mgr != null) {
            mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }


// ---Buton Kontrol -----------------------------------------------------
        // ---   Reklam Goster düğmesi


     /*   reklam= (ImageView) findViewById(R.id.reklam);
        reklamDegisim();*/


        //---------------------------------------------------------------------

        //SplashGoster();
    }     // ------------------------------------------------------ON CREATE  SONU------------------------------------------


    private void splashGecis() {

        sayici = new CountDownTimer(4000, 1000) {

            @Override
            public void onTick(long sure) {

            }


            @Override
            public void onFinish() {

                splashEkrani.setVisibility(View.INVISIBLE);
                appEkrani.setVisibility(VISIBLE);

             //   interstitialAd.loadAd();   // Splash sonrası 1 kez Fcebook Interstitial yüklenmesi

               /* if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("interstitial", "The interstitial wasn't loaded yet.");
                }*/

               mInterstitialAd.show();

            }


        }.start();

        // Toast.makeText(getApplicationContext(),"lkmjlkjlkj",Toast.LENGTH_LONG).show();
    }

   /* private void reklamDegisim() {

        sayici = new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long sure) {

                }


            @Override
            public void onFinish() {

               colicSplashLayout.setVisibility(View.INVISIBLE);
               anaProgramLayout.setVisibility(VISIBLE);

            }


        }.start();

        // Toast.makeText(getApplicationContext(),"lkmjlkjlkj",Toast.LENGTH_LONG).show();
    }*/


    public void ses1(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setBackgroundResource(R.drawable.anne_resim_aktif);
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(1, music1);

        // view.setBackground(getResources().getDrawable(R.drawable.buton_oval));
        //view.setBackground(getDrawable(R.drawable.buton_oval));

        // TODO: Move this method and use your own event name to track your key metrics
        // TODO: Use your own string attributes to track common values over time
        // TODO: Use your own number attributes to track median value over time
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Süpürge"));
        Answers.getInstance().logCustom(new CustomEvent("Süpürge"));


    }

    public void ses2(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        //     view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(2, music2);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Fön"));
        Answers.getInstance().logCustom(new CustomEvent("Fön Makinası"));
    }

    public void ses3(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        //   view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(3, music3);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Çamaşır Makinası"));
        Answers.getInstance().logCustom(new CustomEvent("Çamaşır Makinası"));

    }

    public void ses4(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(4, music4);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Kalp"));
        Answers.getInstance().logCustom(new CustomEvent("Kalp"));

    }

    public void ses5(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(5, music5);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Anne Karnı"));
        Answers.getInstance().logCustom(new CustomEvent("Anne Karnı"));

    }

    public void ses6(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(6, music6);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Pervane"));
        Answers.getInstance().logCustom(new CustomEvent("Pervane"));

    }

    public void ses7(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(7, music7);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Araba"));
        Answers.getInstance().logCustom(new CustomEvent("Araba"));

    }

    public void ses8(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(8, music8);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Duş"));
        Answers.getInstance().logCustom(new CustomEvent("Duş Sesi"));

    }

    public void ses9(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundResource(R.drawable.yagmur_resim_s);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(9, music9);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Yağmur"));
        Answers.getInstance().logCustom(new CustomEvent("Yağmur"));

    }

    public void ses10(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(10, music10);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Beyaz Ses"));
        Answers.getInstance().logCustom(new CustomEvent("Beyaz Gürültü"));

    }

    public void ses11(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(11, music11);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Tren"));
        Answers.getInstance().logCustom(new CustomEvent("Tren"));

    }

    public void ses12(View view) {

        renk_kontrol();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.buton_oval));
        // view.setAlpha(1f);
        //view.setBackgroundColor(Color.rgb(255,205,155));
        sesCal(12, 0);
        Answers.getInstance().logCustom(new CustomEvent("Buton Aktif")
                .putCustomAttribute("Category", "Müzik Kutusu"));
        Answers.getInstance().logCustom(new CustomEvent("Müzik Kutusu"));

    }


    // public View sonRenk;
    public void renk_kontrol() {
        ImageButton a, b, c, d, e, f, g, h, i, j, k, l;
        a = findViewById(R.id.supurge_btn);
        b =  findViewById(R.id.fon_btn);
        c =  findViewById(R.id.annekarni_btn);
        d =  findViewById(R.id.camasir_btn);
        e =  findViewById(R.id.araba_btn);
        f =  findViewById(R.id.tren_btn);
        g =  findViewById(R.id.kalp_btn);
        h =  findViewById(R.id.fan_btn);
        i =  findViewById(R.id.white_btn);
        j =  findViewById(R.id.yagmur_btn);
        k =  findViewById(R.id.musicbox_btn);
        l =  findViewById(R.id.banyo_btn);


        //  "@android:color/background_light

        a.setBackgroundColor(Color.rgb(255, 255, 255));
        b.setBackgroundColor(Color.rgb(255, 255, 255));
        c.setBackgroundColor(Color.rgb(255, 255, 255));
        d.setBackgroundColor(Color.rgb(255, 255, 255));
        e.setBackgroundColor(Color.rgb(255, 255, 255));
        f.setBackgroundColor(Color.rgb(255, 255, 255));
        g.setBackgroundColor(Color.rgb(255, 255, 255));
        h.setBackgroundColor(Color.rgb(255, 255, 255));
        i.setBackgroundColor(Color.rgb(255, 255, 255));
        j.setBackgroundColor(Color.rgb(255, 255, 255));
        k.setBackgroundColor(Color.rgb(255, 255, 255));
        l.setBackgroundColor(Color.rgb(255, 255, 255));
      /*  a.setAlpha(0.5f);
        b.setAlpha(0.5f);
        c.setAlpha(0.5f);
        d.setAlpha(0.5f);
        e.setAlpha(0.5f);
        f.setAlpha(0.5f);
        g.setAlpha(0.5f);
        h.setAlpha(0.5f);
        i.setAlpha(0.5f);
        j.setAlpha(0.5f);
        k.setAlpha(0.5f);
        l.setAlpha(0.5f);*/


    }


//---------------  SES YÜKLE ---------------------------


    //-------------------------------------------------------


//   -------------   SES ÇAL --------------------------------------------


    public void sesCal(int sesKodu, int muzikKodu) {

        musicCode = muzikKodu;
        if (/*mp.isPlaying()*/ sesCaliyormu && (sesKodu == sonSes))   //  Basılan butonun çalan müzik ile aynı olduğunu kontrol ediyor
        {

            renk_kontrol();
            if (muzikKodu == 0) {
                mp.pause();
            } else {
                sesleriSustur();
            }

            sesCaliyormu = false;

        } else if (sesKodu == sonSes)    // Basılan buton duran ses ile aynı olup olmadığını kontrol ediyor
        {
//
            if (muzikKodu == 0) {
                mp.start();
            } else {
                streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
            }
            sesCaliyormu = true;


        } else {
            switch (sesKodu) {  // Basılan butona göre çalma işlemi
                case 1:
                    // mp.stop();
                    //    mp.release();
                    //    mp = MediaPlayer.create(this, R.raw.supurge);
                    //    mp.start();
                    //sesZamanlamasi();
                    //     mp.setLooping(true);

                    sesleriSustur();
                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);

                    sesCaliyormu = true;

                    sonSes = 1;
                    break;
                case 2:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);

                    sesCaliyormu = true;


                    sonSes = 2;
                    break;
                case 3:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);

                    sesCaliyormu = true;

                    sonSes = 3;
                    break;
                case 4:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 4;
                    break;
                case 5:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 5;
                    break;
                case 6:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 6;
                    break;
                case 7:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 7;
                    break;
                case 8:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 8;
                    break;
                case 9:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 9;
                    break;
                case 10:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 10;
                    break;
                case 11:
                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 11;
                    break;
                case 12:
                    //  mp.stop();
                    sesleriSustur();


                    mp = MediaPlayer.create(this, R.raw.musicbox);
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer arg0) {
                            mp.start();

                        }
                    });
                    // mp.start();


                    // sesZamanlamasi();
                    mp.setLooping(true);
                    sonSes = 12;
                    break;

                case 13:

                    sesleriSustur();

                    streamId = soundPool.play(muzikKodu, 1, 1, 0, -1, 1);
                    sesCaliyormu = true;

                    sonSes = 13;
            }
        }

    }

//---------------Sesleri Susturma---------------------

    public void sesleriSustur() {
        soundPool.stop(streamId);
        mp.release();
        //mp.stop();


    }

    public void cagriSesKontrol() {


       /* soundPool.stop(streamId);
        mp.release();
        mp = MediaPlayer.create(this, R.raw.musicbox);*/

        //renk_kontrol();

        sesCal(13, musicSessiz);


    }


//--------------------------------------------------------

    //-------------LOGO Tıklanması Sonucu Çıkan Menü -------------------

    public void logoTiklandi(View view) {
        bilgiGetir bilgi = new bilgiGetir();
        bilgi.show(getFragmentManager(), "my_dialog");
        // Toast.makeText(this, "logo tıklandı bebişim", Toast.LENGTH_SHORT).show();
    }

    //-----------------------------------------------------------------

    //----------   OTOMATİK KAPAMA ZAMANLAYICISI -------------------

    public void Sayici(View view) {

        if (view.getId() == R.id.kapatButon) {
            iptalKontrol = true;
            Calendar simdikiZaman = getInstance();
            simdikiSaat = simdikiZaman.get(HOUR_OF_DAY);
            simdikiDakika = simdikiZaman.get(MINUTE);
            TimePickerDialog zamanDialog;
            zamanDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int saat, int dakika) {
                    int kalanSaat = saat - simdikiSaat;
                    int kalanDakika = dakika - simdikiDakika;
                    int ToplamSure = (kalanSaat * 60 * 60 * 1000) + (kalanDakika * 60 * 1000);
                    if (ToplamSure < 0) {

                        ToplamSure = ((kalanSaat + 24) * 60 * 60 * 1000) + (kalanDakika * 60 * 1000);

                    }
                    sayici = new CountDownTimer(ToplamSure, 1000) {

                        @Override
                        public void onTick(long sure) {
                            if (iptalKontrol) {
                                sure = sure / 1000;
                                int saat = (int) (sure / 3600);
                                sure -= saat * 3600;
                                int dk = (int) (sure / 60);
                                sure -= dk * 60;
                                int sn = (int) sure % 60;
                                // kalanSure.setText("Kalan Süre:"+saat+":"+dk+":"+sn);
                                sayiciText.setText(String.format(FORMAT, saat, dk, sn));
                                //    sayiciText.setText(String.format(FORMAT, saat, dk, sn));
                            } else {
                                cancel();
                            }
                        }

                        @Override
                        public void onFinish() {

                            if (iptalbtn) {
                                iptalbtn = !iptalbtn;
                            } else {
                                Cikis();
                            }
                        }


                    }.start();


                }
            }, simdikiSaat, simdikiDakika, true);

            zamanDialog.setTitle(R.string.zamanlayici_yazi);
            zamanDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.zamanlayici_ok), zamanDialog);
            zamanDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, getString(R.string.zamanlayici_no),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            if (which == DialogInterface.BUTTON_NEGATIVE) {
                                iptalbtn = true;
                                iptalButon.setVisibility(View.GONE);
                                kapatButon.setVisibility(VISIBLE);
                                iptalKontrol = false;
                            }
                        }
                    });


            zamanDialog.show();
            iptalButon.setVisibility(VISIBLE);
            kapatButon.setVisibility(View.GONE);


        } else if (view.getId() == R.id.iptalButon) {
            iptalKontrol = false;
            sayiciText.setText(R.string.app_name);
            iptalButon.setVisibility(View.GONE);
            kapatButon.setVisibility(VISIBLE);

            //   sayici.onFinish();
        }


    }


    //--------------------------------------------------------------

    // Facebook Analitycs   EKleme


    /* This function assumes logger is an instance of AppEventsLogger and has been
     * created using AppEventsLogger.newLogger() call.*/

    public void logSentFriendRequestEvent () {
        AppEventsLogger logger=AppEventsLogger.newLogger(getApplicationContext());
        logger.logEvent("sentFriendRequest");
    }


//   --------------------------------------------------------------------


    // Splash 2 (Custom Toast) Gösterme---------------
/*public void SplashGoster()
{


    new Handler().postDelayed(new Runnable() {


        @Override
        public void run() {

            anaProgramLayout.setVisibility(VISIBLE);
            colicSplashLayout.setVisibility(View.INVISIBLE);
            finish();
        }
    }, SPLASH_TIME_OUT);

    *//*Toast splashToast=new Toast(getApplicationContext());
    splashToast.setGravity(Gravity.CENTER,0,0);
    splashToast.setDuration(Toast.LENGTH_LONG);
    splashToast.setView(viewLayout);
    splashToast.show();*//*

}*/
    //--------------------------------------------------

    // ---------  Reklam kapanma açılma  -----------

    /**
     * Called when leaving the activity
     */
/*    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }*/

    /**
     * Called when returning to the activity
     */
/*    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }*/

    /**
     * Called before the activity is destroyed
     */
/*    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }*/




    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }

     //   if (interstitialAd != null)
     //       {     interstitialAd.destroy();     }

        super.onDestroy();
    }

    // -----  ÇIKIŞ ---------------------------
    public void cikis(View v) {
        Cikis();
    }

    private void Cikis() {
        ((NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancel(1);
        finish();
        System.exit(0);
    }

}   // -----------   ANA SINIFIN SONU    -------------------------

