package com.mycompany.frag;

import android.graphics.*;
import android.widget.*;
import android.content.*;
import java.io.*;
import java.net.*;
import android.util.*;
import android.os.*;

public class Utils
{
	/*
	public void getText(Context c,String src,TextView t1)
	{
		String text = "";
		Cache cache = new Cache(c);
		if(cache.exists(src)==true)
		{
			text = cache.getCache(src);
		}
		else if(cache.isSaved(src))
		{
			text = cache.getSaved(src);
		}
		else
		{
			Fetch f = new Fetch(t1);
			f.execute(MainActivity.url+"Audiobook/"+src);
			
			//text = task(MainActivity.url+"Audiobook/"+src);
			cache.Cache(src,text);
			//	b = task(url+"Audiobook/"+c);
			//	cache.Cache(c,b);
			//b(b,a,c);
		}
		//return text;
		//t1.setText(text);
	}
	*/
	public void loadImage(Context c,String s3,ImageView i1)
	{
		if(s3.contains("DMK"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.dmk);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("POM"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.pom);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("PTA"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.pta);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		
		else if(s3.contains("JOK"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.jok);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("MRL"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.mrl);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("PRO"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.crown);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("ALG"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.alg);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("BBS"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.bbs);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("KYR"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.kyr);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("SUB"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.sub);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("HLT"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.hlt);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("SLF"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.slf);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("NOV"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.slf);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("PNH"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.pnh);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("GYN1"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.ifs);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("BIO1"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.ptm);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("BIO2"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.fbk);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("BIO3"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.air);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("BIO4"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.rop);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("BIO5"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.mit);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else if(s3.contains("BIO6"))
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.sam);
			Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
			i1.setImageBitmap(scale);
		}
		else
		{
			String s4 = s3.substring(0,s3.indexOf("."))+".jpg";
			Cache cache = new Cache(c);
			if(cache.exists(s4))
			{
				Bitmap bm = BitmapFactory.decodeFile(cache.cacheDir()+"/"+s4);
				Bitmap scale = Bitmap.createScaledBitmap(bm,680,400,true);
				i1.setImageBitmap(scale);
			}
			else
			{
				ImgFetch f = new ImgFetch(c,i1,s4);
				f.execute(MainActivity.url+"images/"+s4);
			//	f.execute("https://images-na.ssl-images-amazon.com/images/I/71PW3SyIJSL.jpg");
				
			}
		
		}
		
	}
	
}

class ImgFetch extends AsyncTask<String,String,Boolean>
{
	ImageView iv;
	FileOutputStream os;
	Bitmap scale;
	Context c;
	boolean isDown = false;
	String name;
	Cache cache;
	
	ImgFetch(Context c,ImageView iv,String name)
	{
		this.iv = iv;
		this.c = c;
		this.name = name;
		cache = new Cache(c);
	}
	
	@Override
	protected void onPreExecute()
	{
		// TODO: Implement this method
		super.onPreExecute();
		Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.error);
		scale = Bitmap.createScaledBitmap(bm,680,400,true);
		iv.setImageBitmap(scale);
	}

	@Override
	protected Boolean doInBackground(String[] p1)
	{

		try
		{
			InputStream is = new URL(p1[0]).openConnection().getInputStream();
			byte[] buffer = new byte[1024];
			int bufferLength = 0;
			os = new FileOutputStream(new File(cache.cacheDir()+"/"+name));//ReadActivity.path+"temp.jpg");
			while((bufferLength = is.read(buffer))>0)
			{
				os.write(buffer,0, bufferLength);
			}
			os.flush();
			os.close();
			isDown = true;
			
		}

		catch (Exception e)
		{
			isDown = false;
			Log.e("ImgFetchErr()",e.toString());
			//cache.cacheError("DownErr()  "+e.toString());
		}

		return isDown;
	}

	

	@Override
	protected void onPostExecute(Boolean result)
	{
		// TODO: Implement this method
		super.onPostExecute(result);
		
		if(result==true)
		{
			Cache cache = new Cache(c) ;
			Bitmap bm = BitmapFactory.decodeFile(cache.cacheDir()+"/"+name);//ReadActivity.path+"temp.jpg");
			scale = Bitmap.createScaledBitmap(bm,680,400,true);
			iv.setImageBitmap(scale);
		}
		else
		{
			Bitmap bm = BitmapFactory.decodeResource(c.getResources(),R.drawable.error);
			scale = Bitmap.createScaledBitmap(bm,680,400,true);
			iv.setImageBitmap(scale);
		}
	}
}
	