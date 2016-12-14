package com.self.toollibs;

/**
 * author：ygl_panpan on 2016/12/14 14:13
 * email：pan.lq@i70tv.com
 */
public class TextUtils {

    /**
     * 判空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        return android.text.TextUtils.isEmpty(s) || "null".equalsIgnoreCase(s);
    }

}
