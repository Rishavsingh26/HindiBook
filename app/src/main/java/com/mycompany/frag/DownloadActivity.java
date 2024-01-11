package com.mycompany.frag;
import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.view.*;
import android.database.*;
import android.content.*;
import java.io.*;
import android.util.*;

public class DownloadActivity extends Activity
{
	String file;
	boolean delete = false;
	GridView g1;
	ArrayList<String> list;
	ArrayList<download> data;
	ArrayAdapter<String> ad;
	DatabaseHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		
		g1 = (GridView) findViewById(R.id.downloadGridView);
		
		list = new ArrayList<String>();
		data = new ArrayList<download>();
		db = new DatabaseHelper(this);
		load();
		ad = new ArrayAdapter<String>(this,R.layout.view,R.id.viewTextView,list);
		g1.setAdapter(ad);
		
		g1.setOnItemClickListener(new AdapterView.OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					if(delete==true)
					{
						File f = new File(ReadActivity.path+data.get(p3).src);
						if(f.exists())
						
							f.delete();			
							db.delete(String.valueOf(data.get(p3).id));
							list.remove(p3);
							ad.notifyDataSetChanged();
						    
							Toast.makeText(getApplicationContext(),data.get(p3).name+" Deleted !",Toast.LENGTH_LONG).show();
						

					}
					else
					{
						
					file = data.get(p3).src;
					String s1 = task(ReadActivity.path+data.get(p3).src);
					String s2 = data.get(p3).name;
					String s3 = data.get(p3).src;
					Intent i = new Intent(DownloadActivity.this,ReadActivity.class);
					i.putExtra("s1",s1);
					i.putExtra("s2",s2);
					i.putExtra("s3",s3);
					startActivity(i);
				}}
				
			});
	}
	
			public boolean onCreateOptionsMenu(Menu menu) {
					// Inflate the menu,add items to the action bar if it is present.
					getMenuInflater().inflate(R.menu.menu3, menu);//Menu ResourceFile
					return true;
				}
				@Override
				public boolean onOptionsItemSelected(MenuItem item) {
						switch (item.getItemId()) {
									case R.id.down_item :
	delete = true;								Toast.makeText(getApplicationContext(),"Select  Items to Delete If Done Please Go Back First",Toast.LENGTH_LONG).show();
											return true;
									default:
											return super.onOptionsItemSelected(item);
					}


 }
	private void load()
	{
		Cursor c = db.getAllData();
		while(c.moveToNext())
		{
			data.add(new download(Integer.parseInt(c.getString(c.getColumnIndex(db.COL_1))),c.getString(c.getColumnIndex(db.COL_2)),c.getString(c.getColumnIndex(db.COL_3))));
			list.add(c.getString(c.getColumnIndex(db.COL_2)));
		}
	}
	
	private String task(String u)
	{
		StringBuilder sb = new StringBuilder();
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(u)));
			String t;
			while((t=br.readLine())!=null)
			{
				sb.append(t).append("\n");
			}

		}

		catch (Exception e)
		{
			Log.i("DatabaseTaskErr()-",e.toString());
		}

		return sb.toString().trim();
	}
	
	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		delete = false;
	}
}

class download
{
	int id;
	String name;
	String src;
	public download(int id,String name,String src)
	{
		this.id=id;
		this.name=name;
		this.src=src;

	}

} 