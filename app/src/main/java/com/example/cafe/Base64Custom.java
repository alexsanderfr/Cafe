package com.example.cafe;

import android.util.Base64;

public class Base64Custom {
    public static String encodeBase64(String text) {
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).trim();
    }

    public static String decodeBase64(String encodedText) {
        byte[] decodedBytes = Base64.decode(encodedText, Base64.DEFAULT);
        return new String(decodedBytes);
    }
}
