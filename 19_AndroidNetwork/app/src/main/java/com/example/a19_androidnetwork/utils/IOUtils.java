package com.example.a19_androidnetwork.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/08/05
 * </pre>
 */
public class IOUtils {

    public static void ioClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
