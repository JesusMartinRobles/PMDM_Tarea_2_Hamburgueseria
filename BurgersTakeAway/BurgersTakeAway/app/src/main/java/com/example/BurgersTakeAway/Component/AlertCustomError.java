package com.example.BurgersTakeAway.Component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.BurgersTakeAway.Login;
import com.example.BurgersTakeAway.R;

public class AlertCustomError  {

public AlertDialog getAlert(Context context,String Title, String Msg, int typeAlert ,String btnTitle){
    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
    alertDialog.setTitle(Title);
    alertDialog.setMessage(Msg);
    alertDialog.setButton(typeAlert, btnTitle,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

return  alertDialog;
}
}