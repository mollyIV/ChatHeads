package com.danieldev.windowhead;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Creates the head layer view which is displayed directly on window manager.
 * It means that this view is above every application's view on your phone -
 * until another application does the same.
 */
public class HeadLayer extends View {

    private Context context;
    private FrameLayout frameLayout;
    private WindowManager windowManager;

    public HeadLayer(Context context) {
        super(context);

        this.context = context;

        createHeadView();
    }

    /**
     * Creates head view and adds it to the window manager.
     */
    private void createHeadView() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT;

        frameLayout = new FrameLayout(context);

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(frameLayout, params);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Here is the place where you can inject whatever layout you want.
        layoutInflater.inflate(R.layout.head, frameLayout);
    }

    /**
     * Removes the view from window manager.
     */
    public void destroy() {
        windowManager.removeView(frameLayout);
    }
}
