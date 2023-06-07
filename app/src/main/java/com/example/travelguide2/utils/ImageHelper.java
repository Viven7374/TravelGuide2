package com.example.travelguide2.utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

public class ImageHelper {

    public static Bitmap stringToBitmap(String string){
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray ;
            Decoder decoder = Base64.getDecoder();
            bitmapArray = decoder.decode(string);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray,0,bitmapArray.length);

        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    public static String bitmapToString(Bitmap bitmap){
        String string;
        ByteArrayOutputStream btString = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,btString);
        byte[] bytes = btString.toByteArray();
        Encoder encoder = Base64.getEncoder();
        string = encoder.encodeToString(bytes);
        return string;
    }

}
