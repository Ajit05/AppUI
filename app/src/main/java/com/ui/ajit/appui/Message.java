package com.ui.ajit.appui;

import android.content.Context;
import android.widget.Toast;

public class Message {
	public static void message(Context context,String mesg){
		Toast.makeText(context,mesg,Toast.LENGTH_LONG).show();
	}
	public static void messageInt(Context context,int mesg){
		Toast.makeText(context,mesg,Toast.LENGTH_LONG).show();
	}
}
