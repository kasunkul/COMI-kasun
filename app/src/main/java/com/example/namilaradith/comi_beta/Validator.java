package com.example.namilaradith.comi_beta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Namila Radith on 2016-03-26.
 */
public class Validator {

    public static boolean isEmpty(Integer value) {

        return  (value == null);
    }

    public static boolean isEmpty(String value) {

        return  (value.isEmpty());
    }

    public static boolean isNegative(int value) {

        return  (value < 0);
    }

    public static boolean isDate(String value) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(value);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static boolean isEmail(String value) {
        String EMAIL_REGIX = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_REGIX);
        Matcher matcher = pattern.matcher(value);
        return  (matcher.matches());
    }

    public static boolean isAvailable(String value) {

        return  false;
    }

}
