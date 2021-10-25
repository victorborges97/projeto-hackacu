package br.com.equipe23.divulgacu.config;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

public class Shared {

    public static void openWhatsApp(Activity acc, String number, String body){
        try {
            String pais = "55";

            Intent sendIntent = new Intent(
                    Intent.ACTION_SENDTO,
                    Uri.parse("smsto:" + pais + number + "?body=" + body)
            );
            sendIntent.setPackage("com.whatsapp");
            acc.startActivity(sendIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openMaps(Activity acc, String endereco) {
        try {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+endereco);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            //set package to "com.google.android.apps.maps" so that only google maps is opened.
            mapIntent.setPackage("com.google.android.apps.maps");
            acc.startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void openInstagram(Activity acc, String insta) {
        Uri uri = Uri.parse("http://instagram.com/_u/"+insta);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.setPackage("com.instagram.android");
        try {
            acc.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            acc.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/"+insta)));
        }
    }

}
