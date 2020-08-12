package com.news.news.Support;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.news.news.R;

public class connection {
    Activity activity;
    Dialog dialog;
    public connection(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_loading_layout);
        dialog.show();
    }
    public void hideDialog(){
        dialog.dismiss();
    }


}
