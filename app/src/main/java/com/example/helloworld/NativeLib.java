package com.example.helloworld;

/**
 * Created by caydencui on 2019/3/27.
 */
public class NativeLib {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native OutputStruct Bitmap2Grey(int[] pixels_1, int[] pixels_2, int w, int h);
}
