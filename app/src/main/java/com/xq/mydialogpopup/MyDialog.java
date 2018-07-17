package com.xq.mydialogpopup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


@SuppressWarnings("deprecation")
public class MyDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private int layoutResID;
    private int[] resIdItems;
    private String[] textItems;

    public MyDialog(Context context, int layoutResID, String[] textItems, int[] resIdItems) {
        this(context, layoutResID, textItems, resIdItems, R.style.dialog);
        this.context = context;
        this.textItems = textItems;
        this.layoutResID = layoutResID;
        this.resIdItems = resIdItems;
    }

    public MyDialog(Context context, int layoutResID, String[] textItems, int[] resIdItems, int themeResId) {
        super(context, themeResId);
        this.context = context;
        this.textItems = textItems;
        this.layoutResID = layoutResID;
        this.resIdItems = resIdItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            WindowManager.LayoutParams lp = window.getAttributes();
//            lp.width = display.getWidth() * 4 / 5;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }

        setCanceledOnTouchOutside(true);
        setCancelable(true);

        if (textItems == null || resIdItems == null) {
            return;
        }
        if (textItems.length != resIdItems.length) {
            return;
        }

        for (int i = 0; i < resIdItems.length; i++) {
            int resItem = resIdItems[i];
            String textItem = textItems[i];
            View view = findViewById(resItem);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setText(textItem);
                textView.setOnClickListener(this);
            }
        }
    }


    private OnDialogItemClickListener listener;

    public interface OnDialogItemClickListener {
        void OnDialogItemClick(MyDialog dialog, View view);
    }

    public void setOnDialogItemClickListener(OnDialogItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.OnDialogItemClick(this, view);
    }


}
