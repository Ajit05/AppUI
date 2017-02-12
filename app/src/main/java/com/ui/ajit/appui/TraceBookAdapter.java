package com.ui.ajit.appui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

public class TraceBookAdapter {

AjHelper helper;
	
	public TraceBookAdapter(Context context){
		helper = new AjHelper(context);
	}
	public long insertData(String name,String LastName,String mobileNo,String email,String password,String confirmpassword){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(AjHelper.NAME, name);
		contentValues.put(AjHelper.LAST_NAME, LastName);
		contentValues.put(AjHelper.MOBILE, mobileNo);
		contentValues.put(AjHelper.EMAIL,email);
		contentValues.put(AjHelper.PASSWORD,password);
		contentValues.put(AjHelper.CONFIRMPASS, confirmpassword);
		//contentValues.put(AjHelper.BIRTHDAY, birthDate);
		//contentValues.put(AjHelper.UGENDER, gender);
		//contentValues.put(AjHelper.UTERM, tAnda);
		long id = db.insert(AjHelper.TABLE_NAME,null,contentValues);
		db.close();
		return id;
	}
	
	public String getPassword(){
		String[] userPassword = {AjHelper.PASSWORD};
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor=db.query(AjHelper.TABLE_NAME,userPassword,null,null,null,null,null);
		String s_userpassword = "Ajit";
 		while(cursor.moveToNext()){
		int index = cursor.getColumnIndex(AjHelper.PASSWORD);
		
		s_userpassword = cursor.getString(index);
		
 		}
 		return s_userpassword; 
	}
	
	//to get uniqueId
	public String getMobileNo(){
		SQLiteDatabase db = helper.getReadableDatabase();
		String[] mobile = {AjHelper.MOBILE};
		String s_uniqueMobile ="hello";
		
		Cursor cursor = db.query(AjHelper.TABLE_NAME,mobile,null,null,null,null,null);
		
		while(cursor.moveToNext()){
			int index = cursor.getColumnIndex(AjHelper.MOBILE);
			s_uniqueMobile = cursor.getString(index);
			
		}
		return s_uniqueMobile;
	}
	
	//to get userName
		public String getEmail(){
			SQLiteDatabase db = helper.getReadableDatabase();
			String[] name = {AjHelper.EMAIL};
			String s_userEmail="ajity050@gmail.com";
			
			Cursor cursor = db.query(AjHelper.TABLE_NAME,name,null,null,null,null,null);
			
			while(cursor.moveToNext()){
				int index = cursor.getColumnIndex(AjHelper.EMAIL);
				s_userEmail = cursor.getString(index);
				
			}
			return s_userEmail;
		}
		
		public String getUserFirstName(String email){
			SQLiteDatabase db = helper.getReadableDatabase();
			String[] name = {AjHelper.NAME};  //projection
			String[] selectionArgs ={email};
 			Cursor cursor = db.query(AjHelper.TABLE_NAME,name,AjHelper.EMAIL +"=?",selectionArgs,null,null,null);
			String UfirstName="Ajit";
			int index0;
			while(cursor.moveToNext()){
				index0 = cursor.getColumnIndex(AjHelper.NAME);
				UfirstName = cursor.getString(index0);
			}
			return UfirstName;
		}

	public String getUserLastName(String email){
		SQLiteDatabase db = helper.getReadableDatabase();
		String[] name = {AjHelper.LAST_NAME};
		String[] selectionArgs ={email};
		Cursor cursor = db.query(AjHelper.TABLE_NAME,name,AjHelper.EMAIL +"=?",selectionArgs,null,null,null);
		String UlastName="Ajit";
		int index0;
		while(cursor.moveToNext()){
			index0 = cursor.getColumnIndex(AjHelper.LAST_NAME);
			UlastName = cursor.getString(index0);
		}
		return UlastName;
	}
	
	
  class AjHelper extends SQLiteOpenHelper{
		private static final String DATABASE_NAME="tracebookdatabase";
		private static final String TABLE_NAME="TBDATABASE";
		private static final int DATABASE_VERSION=1;
		private static final String UID="_id";
		private static final String NAME="Name";
		private static final String LAST_NAME="LastName";
		private static final String MOBILE="MOBILE";
		private static final String EMAIL="EMAIL";
		private static final String PASSWORD = "Password";
		private static final String CONFIRMPASS="CONFIRMPASSWORD";
	    private static final String BIRTHDAY="BIRTHDAY";
	    //private static final String UGENDER="GENDER";
	    //private static final String UTERM="TERMS";

		private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+LAST_NAME+" VARCHAR(255), "+MOBILE+" VARCHAR(255), "+EMAIL+" VARCHAR(255),"+PASSWORD+" VARCHAR(255), "+CONFIRMPASS+" VARCHAR(255));";
		private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
		private Context context;
		public AjHelper(Context context) {
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
			this.context = context;
		
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
			db.execSQL(CREATE_TABLE);
			//Message.message(context,"onCreate called");
			}catch(SQLException e){
				 
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db , int oldVersion, int newVersion) {
			try{
				db.execSQL(DROP_TABLE);
				onCreate(db);
				//Message.message(context, "onUpgrade called");
				}catch(SQLException e){
					 
				}
			
		}

  }

}
