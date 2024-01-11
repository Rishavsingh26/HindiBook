package com.mycompany.frag;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.graphics.Typeface;
import java.util.*;
import android.view.View;
import android.view.*;
import android.app.*;
import android.content.*;
import android.widget.*;
import android.graphics.*;
import java.io.*;
import android.database.sqlite.*;
import android.database.*;
import android.os.*;
import java.net.*;
import java.util.*;
import android.util.*;

public class ReadActivity extends Activity
{
	static final String path = "/sdcard/Audiobook/";
	TextView t1;
	ImageView i1;
	LinearLayout l1;
	TextToSpeech tts;
	String s1,s2,s3;
	boolean isSpeaking = false,check = true,show = true,done = false, isPro = false;
	SharedPreferences sp;
	DatabaseHelper db;
	Cache cache;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		l1 = (LinearLayout) findViewById(R.id.readLinearLayout);
		t1 = (TextView) findViewById(R.id.readTextView);
		i1 = (ImageView) findViewById(R.id.readImageView);
		
		sp = getSharedPreferences("setting",0);
		db = new DatabaseHelper(this);
	    cache = new Cache(this);
	    check = checkTts();	t1.setTypeface(Typeface.createFromAsset(getAssets(),sp.getString("typeface","MANGAL.TTF")));
		//String s = ("हर इंसान अपने जीवन में बहुत सी गलतियां करता और असफल होता है। अगर आप निरंतर प्रयास कर रहे है तो आपके लिए असफल होना बुरी बात नहीं बल्कि अच्छी बात है क्योंकि हर सफल व्यक्ति ने यह माना है कि ");
		//t1.setText();
		//t1.setText(s);
	
		t1.setTextSize(sp.getInt("textSize",19));
		l1.setBackgroundColor(Color.parseColor(sp.getString("theme","#FFAB00")));
		if(sp.getString("theme","#000000").equals("#FFFFFF"))
		{
			t1.setTextColor(Color.BLACK);
		}
		else
		t1.setTextColor(Color.WHITE);
		
		/*
		tts = new TextToSpeech(this,new TextToSpeech.OnInitListener()
			{

				@Override
				public void onInit(int p1)
				{
					if(p1==TextToSpeech.SUCCESS)
					{
						//Log.i("tts","success");
						int res = tts.setLanguage(Locale.forLanguageTag("hi"));
						if(res==TextToSpeech.LANG_MISSING_DATA)
						{
						//	Log.i("tts","data missing");
							show("Missing Hindi Data");
						}
						else
						{
						//	Log.i("tts","no missing data");
						}
						if(res==TextToSpeech.LANG_NOT_SUPPORTED)
						{
						//	Log.i("tts","not supported");
						show("Device not Support Hindi Tts");
						}
						else
						{
						//	Log.i("tts","lang supported");
						}
					}
					else
					{
					//	Log.i("tts","failed");
					show("Device not Support Tts");
					}
					//tts.setLanguage((Locale.forLanguageTag("hi")));
				}


			});
	    //s1 = read();
	    */
		if(getIntent()!=null)
		  {
		//	s1 = getIntent().getExtras().getString("s1");
			s2 = getIntent().getExtras().getString("s2");
			s3 = getIntent().getExtras().getString("s3");
			ActionBar ab = getActionBar();
			ab.setTitle(s2);
			if(sp.getBoolean("pro",false)==true)
			{
				s3 = s3.replace(".pro",".txt");
			}
		   // t1.setText(s1);
		}
		dur();
		getText();
		Utils u = new Utils();
		//t1.setText(u.getText(this,s3))
		//u.getText(this,s3,t1);
		u.loadImage(this,s3,i1);
		//loadImage();
	

	}
	
	private void auto()
	{
		new CountDownTimer(200,100)
		{

			@Override
			public void onTick(long p1)
			{
				// TODO: Implement this method
			}

			@Override
			public void onFinish()
			{
				speak();
				show("Auto Speaking");
			}


		}.start();
	}
	
	private void getText()
	{
		if(cache.exists(s3)==true)
		{
			if(sp.getBoolean("pro",false)==true)s3 = s3.replace(".pro",".txt");
			s1 = cache.getCache(s3);
			t1.setText(s1);
			if(sp.getBoolean("speak",true)==true)auto();
		}
		else if(cache.isSaved(s3))
		{
			if(sp.getBoolean("pro",false)==false)s3 = s3.replace(".pro",".txt");
			s1 = cache.getSaved(s3);
			t1.setText(s1);
			if(sp.getBoolean("speak",true)==true)auto();	
		}
		else
		{
			done = false;
			if(s3.endsWith(".pro"))
			{
			if(sp.getBoolean("pro",false)==true)
			{
				s3 = s3.replace(".pro",".txt");
				Fetch f = new Fetch();
				f.execute(MainActivity.url+"Audiobook/"+s3);
				
			}
			else
			{
				t1.setText("इसे पढने के लिय अपको हमारा PRO पैक ACTIVATE कराना होगा ।\nअभी PRO User मे जाए ।\nअपना पलान चुनकर Subscribe करे ।\n\nThanks\nTeam HindiBook");
			}	
			}
			if(s3.endsWith(".txt"))
			{
				Fetch f = new Fetch();
				f.execute(MainActivity.url+"Audiobook/"+s3);
			}
		}
	}
	
	@Override
	protected void onPause()
	{
		
		super.onPause();
		show = false;
	}
	@Override
	protected void onResume()
	{
		
		super.onResume();
		show = true;
		/*
		  if(sp.getBoolean("speak",false)==true)
		{
			
		 if(speak==false)
		{
			speak=true;
			show("Auto Speaking");
		    speak();
		    
		}
		
		}*/
		
	}
	
	private boolean checkTts()
	{
		
		tts = new TextToSpeech(this,new TextToSpeech.OnInitListener()
			{
				
				@Override
				public void onInit(int p1)
				{
					if(p1==TextToSpeech.SUCCESS)
					{
						//Log.i("tts","success");
						int res = tts.setLanguage(Locale.forLanguageTag("hi"));
						if(res==TextToSpeech.LANG_MISSING_DATA)
						{
							//	Log.i("tts","data missing");
							show("Missing Hindi Data");
							check = false;
						}
						else
						{
							//	Log.i("tts","no missing data");
						}
						if(res==TextToSpeech.LANG_NOT_SUPPORTED)
						{
							//	Log.i("tts","not supported");
							show("Device not Support Hindi Tts");
							check = false;
						}
						else
						{
							//	Log.i("tts","lang supported");
						}
					}
					else
					{
						//	Log.i("tts","failed");
						show("Device not Support Tts");
						check = false;
					}
					//tts.setLanguage((Locale.forLanguageTag("hi")));
				}


			});
			return check;
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) { 
		//inflate the menu resource file in your activity 
		getMenuInflater().inflate(R.menu.menu2, menu); 
		return true; 
	} 

	@Override 
	public boolean onOptionsItemSelected(MenuItem item) { 
		//code for item select event handling 
		switch(item.getItemId()){ 
		    case R.id.read_item1 : save(); break;
			case R.id.read_item2 : b(); break;
			case R.id.read_item3 : a(); break; 
			case R.id.read_item4 : d(); break;
			case R.id.read_item5 : c(); break;
			case R.id.read_item6 : e(); break;


		} 

		return super.onOptionsItemSelected(item); 
	} 
	

	public void onClick(View v)
	{
		
		   // speak();
		    //show("Staring Speech Please Wait");
	}
	
	private void speak()
	{
		if(check ==true)
		{
		runOnUiThread(new Runnable()
			{

				@Override
				public void run()
				{
				
					tts.setSpeechRate(sp.getFloat("speed",0.9f));	tts.speak(s1,TextToSpeech.QUEUE_FLUSH,null);
					
				//	speak = false;
				}
				
			
		});
		}else
		{
			isSpeaking = false;
			show("Hindi tts Not Supported");
		}
	
	}
	
	
	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method

		if(tts!=null)
		{
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	} 

	private void save() 
	{
		
		try
		{
			File f1 = new File(path);
			if(!f1.exists())
				f1.mkdir();
			
			FileOutputStream f = new FileOutputStream(new File(path+s3));
			f.write(s1.getBytes());
			f.close();
			
			if(db.check(s3)==false)
			{
				boolean res = db.insert(s2,s3);
				if(res==true)
					show("Saved !");
				else
					show("Error Ocurred While Saving");
			}
			else
			{
				show("Already Saved");
			}
			}
		catch (Exception e)
		{
			show(e.toString());
		}
		}
		
	
	private void a()
	{
		final CharSequence[] items = { "16sp", "17sp", "18sp", "19sp", "20sp", "21sp", "22sp", "23sp", "24sp" };
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		
		ad.setTitle("Select TextSize  : "+sp.getInt("textSize",18));
		ad.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which)
					{
						case 0: t1.setTextSize(16); db.save("textSize",16); break;
						case 1: t1.setTextSize(17); db.save("textSize",17); break;
						case 2: t1.setTextSize(18); db.save("textSize",18); break;
						case 3: t1.setTextSize(18); db.save("textSize",19); break;
						case 4: t1.setTextSize(20); db.save("textSize",20); break;
						case 5: t1.setTextSize(21); db.save("textSize",21); break;
						case 6: t1.setTextSize(22); db.save("textSize",22); break;
						case 7: t1.setTextSize(23); db.save("textSize",23); break;
						case 8: t1.setTextSize(24); db.save("textSize",24); break;
					}
					show(items[which]+" Saved");
					}});
			ad.show();								
	}
	
	private void show(String t)
	{
		Toast.makeText(getApplicationContext(),t, Toast.LENGTH_SHORT).show();
	}
	
	private void b()
	{
		final CharSequence[] items = { "ON", "OFF"};
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		String t = "OFF";
		if(sp.getBoolean("speak",true)==true)t = "ON";
		ad.setTitle("AutoSpeak : "+t);
		ad.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which)
					{
						case 0 : isSpeaking = true;SharedPreferences.Editor edit = sp.edit();edit.putBoolean("speak",true);edit.commit(); show("AutoSpeak Enabled"); break;
						case 1 : isSpeaking = false; SharedPreferences.Editor edit2 = sp.edit();edit2.putBoolean("speak",false);edit2.commit(); show("AutoSpeak Disabled"); break;
					}

				}
			});

		ad.show();

	}
	
	private void d()
	{
		final CharSequence[] items = { "Style 1", "Style 2", "Style 3" };
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
String style;
		if(sp.getString("typeface","MANGAL.TTF").equalsIgnoreCase("aarti.ttf"))
			style = "Style 1";
		if(sp.getString("typeface","MANGAL.TTF").equalsIgnoreCase("mangalb.ttf"))
			style = "Style 2";
		else style = "Style 3";
		ad.setTitle("Select Fonts  : "+style);
		ad.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which)
					{
						case 0: t1.setTypeface(Typeface.createFromAsset(getAssets(),"aarti.ttf")); db.save("typeface","aarti.ttf"); break;
						case 1: t1.setTypeface(Typeface.createFromAsset(getAssets(),"mangalb.ttf")); db.save("typeface","mangalb.ttf"); break;
					    case 2: t1.setTypeface(Typeface.createFromAsset(getAssets(),"MANGAL.TTF")); db.save("typeface","MANGAL.TTF"); break;
					
					}
					show(items[which]+" Saved");
				}});
		ad.show();								
	}
	
	private void e()
	{
		final CharSequence[] items = { " Slowest", " Slower", " Slow"," Medium", " Fast", " Faster", " Fastest" };
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		String spd = "";
		if(sp.getFloat("speed",0.9f)==0.6f)spd = "Slowest ";
		if(sp.getFloat("speed",0.9f)==0.7f)spd = "Slower ";
		if(sp.getFloat("speed",0.9f)==0.8f)spd = "Slow ";
		if(sp.getFloat("speed",0.9f)==0.9f)spd = "Medium ";
		if(sp.getFloat("speed",0.9f)==1.0f)spd = "Fast ";
		if(sp.getFloat("speed",0.9f)==1.1f)spd = "Faster ";
		if(sp.getFloat("speed",0.9f)==1.2f)spd = "Fastest ";
		ad.setTitle("Select Speech Speed  :-  "+spd);
		ad.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which)
					{
						case 0 : tts.setSpeechRate(0.6f); db.save("speed",0.6f); break;	
						case 1 : tts.setSpeechRate(0.7f); db.save("speed",0.7f); break;
						case 2 : tts.setSpeechRate(0.8f); db.save("speed",0.8f); break;
						case 3 : tts.setSpeechRate(0.9f); db.save("speed",0.9f); break;
						case 4 : tts.setSpeechRate(1f); db.save("speed",1f); break;
						case 5 : tts.setSpeechRate(1.1f); db.save("speed",1.1f); break;
						case 6 : tts.setSpeechRate(1.2f); db.save("speed",1.2f); break;
					
					}
					show(items[which]+" Saved");
				}
		});
		ad.show();	
	}
	
	private void c()
	{
		final CharSequence[] items = { "Black", "White", "Orinal", "Orange", "Pink", "Green", "Purple", "Sky Blue" };
		AlertDialog.Builder ad = new AlertDialog.Builder(this);

		ad.setTitle("Select Background : ");
		ad.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which)
					{
						
						case 0: l1.setBackgroundColor(Color.parseColor("#000000")); db.save("theme","#000000"); break;
						case 1: l1.setBackgroundColor(Color.parseColor("#FFFFFF")); db.save("theme","#FFFFFF"); break;
						case 2: l1.setBackgroundColor(Color.parseColor("#FFAB00")); db.save("theme","#FFAB00"); break;
						case 3: l1.setBackgroundColor(Color.parseColor("#F38028")); db.save("theme","#F38028"); break;
						case 4: l1.setBackgroundColor(Color.parseColor("#FF0078")); db.save("theme","#FF0078"); break;
						case 5: l1.setBackgroundColor(Color.parseColor("#008800")); db.save("theme","#008800"); break;
						case 6: l1.setBackgroundColor(Color.parseColor("#00057E")); db.save("theme","#00057E"); break;
						case 7:l1.setBackgroundColor(Color.parseColor("#22839F")); db.save("theme","#22839F"); break;
					
						
					}
					
					show(items[which]+" Theme Saved");
				}
			});

		ad.show();

	}
	
	private void dur()
	{
	//	final String[] time = new String[]{"25000","1250000","50000"};
		final Handler h = new Handler();
		Thread t = new Thread(new Runnable()
			{
				int j = 0;
				@Override
				public void run()
				{
					while(j<3)
					{
					 	try
					{
						Thread.sleep(40000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
						h.post(new Runnable()
							{
								@Override
								public void run()
								{
									if(show==true)
									{
										ad();
										j++;
									}
								}
							});
					}}
			});
		t.start();
	}
	
	private void ad()
	{
		
		String[] tips = new String[]{
		                             //"अश्वगंधा क्या है ?\nRead Now On HindiBook","जेल से निकलना है तो सुरंग बनाइय\nRead Now On HindiBook",
		//	"लकड़ी का कटोरा\nRead Now On HindiBook","आपकी लाइफ कैसी होगी ?\nRead Now on HindiBook","Crack Funny Jokes\n\nRead Now On HindiBook","बैल और मेढ़क\nRead Now On HindiBook","काबिलियत की पहचान\nRead Now On HindiBook","ऊपर उठना है तो खुद को हल्का करो\nRead Now On HindiBook","मकड़ी की सीख\nRead Now On HindiBook","बदले की आग\nRead Now On HindiBook"
		"अश्वगंधा क्या है ?","जेल से निकलना है तो सुरंग बनाइय","लकड़ी का कटोरा","Internet से FREE SMS कैसे भेजे","आपकी लाइफ कैसी होगी ?","मकड़ी की सीख","Roposo का मालिक कौन है ?","Paytm कंपनी का मालिक कौन है ?","~ मनोवृत्ति ~ मुंशी प्रेमचंद्र की कहानियाँ","~ सुभागी ~ मुंशी प्रेमचंद्र की कहानियाँ","Roposo का मालिक कौन है ?","कल आप क्या करेंगे","~ पंचपरमेश्वर ~ मुंशी प्रेमचंद्र की कहानियाँ"
		
 };	
		
       final AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setCancelable(false);
		show = false;
	//	ad.setMessage(tips[i]);
		TextView tv = new TextView(this);
		tv.setTextSize(19);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.parseColor("#FFFFFF"));
		tv.setBackgroundColor(Color.parseColor(sp.getString("theme","#FFAB00")));
		tv.setTypeface(Typeface.createFromAsset(getAssets(),"MANGAL.TTF"));
		Random r = new Random();
		int i = r.nextInt(tips.length);
		if(i<tips.length) 
		tv.setText(tips[i]+"\nअभी पढे हिंदीबुक पर ।" );
		ad.setView(tv);
		ad.setPositiveButton("Close Me",new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					show = true;
				}
			});
		AlertDialog alertdialog = ad.create();
		alertdialog.show();
		/*
		runOnUiThread(new Runnable()
			{

				@Override
				public void run()
				{
					final AlertDialog alertdialog = ad.create();
						alertdialog.show();
					try
					{
						Thread.sleep(4000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
						alertdialog.cancel();
						show = true;
					}
					alertdialog.cancel();
					show = true;
				}
				
			
		});*/
		
	}
	
class Fetch extends AsyncTask<String,String,String>
{
	@Override
	protected void onPreExecute()
	{
			// TODO: Implement this method
		super.onPreExecute();
		t1.setText("Connecting . . .\n");
	}
	
	@Override
	protected String doInBackground(String[] p1)
	{

		StringBuilder sb = new StringBuilder();
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL(p1[0]).openConnection().getInputStream()));
			String t;
			while((t=br.readLine())!=null)
			{
				sb.append(t).append("\n");
				publishProgress(t+"\n");
				Thread.sleep(100);
			}
			/*	SharedPreferences.Editor e = sp.edit();
			 e.putLong("data_used",sp.getLong("data_used",0)+sb.length());
			 e.commit();*/
		}

		catch (Exception e)
		{
			Log.e("DownErr()",e.toString());
			//cache.cacheError("DownErr()  "+e.toString());
		}

		return sb.toString().trim();
	}
	
	@Override
	protected void onProgressUpdate(String[] values)
	{
		// TODO: Implement this method
		super.onProgressUpdate(values);
		t1.append(values[0]);
	}
	
	@Override
	protected void onPostExecute(String result)
	{
		// TODO: Implement this method
		super.onPostExecute(result);
		if(!result.equals(""))
		{
			cache.Cache(s3,result);
			s1 = cache.getCache(s3);
			t1.setText(s1);
			if(sp.getBoolean("speak",true)==true)auto();
		}
	}

}
}

class DatabaseHelper extends SQLiteOpenHelper
{
	public static final String DB_NAME = "AUDIOBOOK.db";
	public static final String TABLE_NAME = "DOWNLOADS";
	public static final String COL_1 = "ID";
	public static final String COL_2 = "NAME";
	public static final String COL_3 = "SRC";
	SharedPreferences sp;
	Context c;

	public DatabaseHelper(Context c)
	{
		super(c,DB_NAME,null,1);
		this.c=c;

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{

		db.execSQL("create table if not exists "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR,SRC VARCHAR)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int p2, int p3)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public void save(String key,String value)
	{
		sp = c.getSharedPreferences("setting",0);
		SharedPreferences.Editor edit = sp.edit();
		edit.putString(key,value);
		edit.commit();
	}
	
	public void save(String key,int value)
	{
		sp = c.getSharedPreferences("setting",0);
		SharedPreferences.Editor edit = sp.edit();
		edit.putInt(key,value);
		edit.commit();
	}
	
	public void save(String key,float value)
	{
		sp = c.getSharedPreferences("setting",0);
		SharedPreferences.Editor edit = sp.edit();
		edit.putFloat(key,value);
		edit.commit();
	}
	
	public boolean insert(String name,String src)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(COL_2,name);
		cv.put(COL_3,src);
		long res = db.insert(TABLE_NAME,null,cv);
		if(res==-1)
			return false;
		else
			return true;
	}

	public Cursor getAllData()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
		return res;

	}

	public Integer delete(String id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		return db.delete(TABLE_NAME, "ID = ?",new String[] {id});


	}

	public String path()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		return db.getPath();
	}

	public boolean check(String src)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("select * from "+TABLE_NAME+" where "+COL_3+" = "+"'"+src+"'",null);
		return c.getCount()>0 ? true:false;
	}

	/*	FileChannel src =new FileInputStream(currentDB).getChannel();
	 FileChannel dst =new FileOutputStream(backupDB).getChannel();  
	 dst.transferFrom(src,0, src.size());
	 Toast.makeText(getApplicationContext(),"Backup Complete",Toast.LENGTH_SHORT).show();

	 src.close();     
	 dst.close();*/
}