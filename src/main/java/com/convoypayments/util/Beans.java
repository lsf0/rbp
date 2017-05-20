package com.convoypayments.util;


import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class Beans {

    public static void fill(Object obj) {
        Field[] fs = obj.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fs, true);


        for (Field f : fs) {

            Class<?> c = f.getType();

            try {
                if(f.get(obj)!=null)
                    continue;

                if (c == String.class)
                    f.set(obj, randStr());

                if (c == BigDecimal.class)
                    f.set(obj, BigDecimal.valueOf(Math.random() * Integer.MAX_VALUE));

                if (c == BigInteger.class)
                    f.set(obj, BigInteger.valueOf(new Random().nextInt()));

                if (c == Date.class)
                    f.set(obj, new Date((long) (Math.random() * System.currentTimeMillis())));

            } catch (IllegalAccessException e) {

                e.printStackTrace();
            }


        }

    }

    private static String randStr() {
        char[] a = new char[1];
        for (int i = 0; i < a.length; i++) {
            a[i] = randChar();
        }
        return new String(a);
    }


    private static char randChar() {
        return (char) (new Random().nextInt(94) + 32);
    }

    public static void main(String[] args) {
        System.out.println(randChar());
    }

}
