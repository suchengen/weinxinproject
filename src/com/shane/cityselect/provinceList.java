package com.shane.cityselect;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.shane.weixin.R;

public class provinceList extends Activity {

	Button exit;
	
	ListView lv;
	Context mcontext;
	
	String provinces[][];
	int provinceCount=0;
	
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_provincelist);

	        exit = (Button)findViewById(R.id.exit_selectprovince);
	        exit.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					provinceList.this.finish();
				}});
	        
	        mcontext=this;
	        lv=(ListView)findViewById(R.id.listview_province);
	       
	        
	        //��ʼ
	        MyDatabase myDB = new MyDatabase(this);
	        Cursor cProvinces = myDB.getProvinces();
	        provinceCount=cProvinces.getCount();
	        provinces=new String[provinceCount][2];
	        
	        for(int j=0;j<provinceCount;j++)
	        {
	        	provinces[j][0]=cProvinces.getString(0);
	        	provinces[j][1]=cProvinces.getString(1);	
	        	cProvinces.moveToNext();
	        }
	        

	        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
	        lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					//Toast.makeText(mcontext,p[position], 1).show();
					Intent i=new Intent(mcontext,cityList.class);
					i.putExtra("provinceid", provinces[position][0]);
					i.putExtra("provincename", provinces[position][1]);
					startActivityForResult(i, 1);
					int version = Integer.valueOf(android.os.Build.VERSION.SDK);     
            		if(version  >= 5) {     
            		     //overridePendingTransition(R.anim.zoomin, R.anim.zoomout);  //��Ϊ�Զ���Ķ���Ч����������Ϊϵͳ�Ķ���Ч��  
            			//overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    
            		    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_out);  
            		}  
				}
			});
	    }

	
	protected void onActivityResult( int resultCode,int re, Intent data) {
		String cityname;
		if(data==null)
		{
			return;
		}
		cityname=data.getStringExtra("cityname");
		returnResult(cityname);	
	}
    
	void returnResult(String sdata) {
		Intent result = new Intent();
		result.putExtra("cityname", sdata);
		setResult(Activity.RESULT_OK, result);
		finish();
	}

	public List<String> getData() {

		List<String> ls=new ArrayList<String>();
		ls=asList(provinces);
		return ls;
	}
	
	//�ַ�����ת��list
	public List<String>  asList(String s[][]){
		List<String> l=new ArrayList<String>();
		for(int i=0;i<provinceCount;i++)
		{
			if(s[i][1]!=null)
				l.add(s[i][1]);
		}
		return l;	
	}
}
