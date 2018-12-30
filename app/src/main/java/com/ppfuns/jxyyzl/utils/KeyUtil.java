package com.ppfuns.jxyyzl.utils;

import java.io.IOException;

/**
 * Author: Fly
 * Time: 18-1-8 上午10:11.
 * Discription: This is KeyUtil
 */

public class KeyUtil {

    public static void SendKey(int keycode) {
        try {
            String keyCommand = "input keyevent " + keycode;
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(keyCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
