package com.mozo.meyaz.colicbaby;

/**
 * Created by mehmet on 17.02.2017.
 */


    import android.app.AlertDialog;
    import android.app.Dialog;
    import android.app.DialogFragment;
    import android.content.ActivityNotFoundException;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.net.Uri;
    import android.os.Bundle;
    import android.support.annotation.NonNull;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

public class bilgiGetir extends DialogFragment {

        LayoutInflater inflater;
        View v;
       // ImageView face,twit,play;
        ImageView mail;

        TextView tv;

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState){
            inflater = getActivity().getLayoutInflater();
            v = inflater.inflate(R.layout.bilgi_ekrani, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


         //   face=(ImageView)v.findViewById(R.id.face);
            mail=v.findViewById(R.id.mail);
         //   play=(ImageView)v.findViewById(R.id.play);

            tv = v.findViewById(R.id.privacy);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("url","url");
                    Uri uri = Uri.parse("http://www.bilisimkalesi.com/privacy_policy"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            });


            /*

            // ----  Google Play Bağlantısı-----

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    Uri uri = Uri.parse("market://details?id=com.mozo.meyaz.colicbaby");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
// To count with Play market backstack, After pressing back button,
// to taken back to our application, we need to add following flags to intent.
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=com.mozo.meyaz.colicbaby")));
                    }

                }
            });

*/

//  --- FaceBook Bağlantısı ---------------------------------------

            /*


            face.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ba","sıldı");
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(getActivity());
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);







                  //  sendEmail();
                }
            });
        */


            //  MAİL Gönderme ------------------------------------------

            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /* Intent intent = null;
                    try {
// get the Twitter app if possible
                        getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=" + "4861683069"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } catch (Exception e) {
// no Twitter app, revert to browser
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MOZOyazilim"));
                    }
                    getActivity().startActivity(intent);*/


                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"colicbabyapp@gmail.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "");
                    i.putExtra(Intent.EXTRA_TEXT   , "");
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });





            builder.setView(v).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                }
            });




            return builder.create();
        }

        /*protected void sendEmail() {
            String mailto = "mailto:bob@example.org" +
                    "?cc=" + "alice@example.com" +
                    "&subject=" + Uri.encode(subject) +
                    "&body=" + Uri.encode(bodyText);

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailto));

            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
            }
        }*/


        /*

        public static String FACEBOOK_URL = "https://www.facebook.com/profile.php?id=222144951873972";
        public static String FACEBOOK_PAGE_ID = "222144951873972";

        //method to get the right URL to use in the intent
        public String getFacebookPageURL(Context context) {
            PackageManager packageManager = context.getPackageManager();
            try {
                int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                if (versionCode >= 3002850) { //newer versions of fb app
                    return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
                } else { //older versions of fb app
                    return "fb://page/" + FACEBOOK_PAGE_ID;
                }
            } catch (PackageManager.NameNotFoundException e) {
                return FACEBOOK_URL; //normal web url
            }
        }

        */



    }





