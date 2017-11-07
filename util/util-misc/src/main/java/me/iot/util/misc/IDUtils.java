package me.iot.util.misc;

import com.google.common.primitives.UnsignedInts;

public class IDUtils {

    private static byte[] KEY_ARRAY = {0x10, 0x21, 0x32, 0x43, 0x54, 0x65, 0x76, 0x11, 0x22, 0x33};

    public static long generateId(long id) {
        int idInt = (int) id;
        int userIdInt = Skip32Utils.encrypt(idInt, KEY_ARRAY);
        return UnsignedInts.toLong(userIdInt);
    }

    public static void test(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(IDUtils.generateId(i));
        }
    }

}
