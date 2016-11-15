package model;

import java.util.Random;

/**
 * Created by VuYeK on 13.01.2016.
 */
public class Captcha {

    public String randomString(int len) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        //System.out.println(output);
        return output;
    }
}
