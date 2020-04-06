package com.bf.qinx.hostofplugin.merge;

import android.annotation.SuppressLint;
import android.os.Build;

import java.io.Closeable;
import java.util.zip.ZipFile;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/4/5 下午10:39</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
class SharePatchFileUtil {

    /**
     * Closes the given {@code obj}. Suppresses any exceptions.
     */
    @SuppressLint("NewApi")
    public static void closeQuietly(Object obj) {
        if (obj == null) return;
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (Throwable ignored) {
                // Ignored.
            }
        } else if (Build.VERSION.SDK_INT >= 19 && obj instanceof AutoCloseable) {
            try {
                ((AutoCloseable) obj).close();
            } catch (Throwable ignored) {
                // Ignored.
            }
        } else if (obj instanceof ZipFile) {
            try {
                ((ZipFile) obj).close();
            } catch (Throwable ignored) {
                // Ignored.
            }
        } else {
            throw new IllegalArgumentException("obj: " + obj + " cannot be closed.");
        }
    }
}
