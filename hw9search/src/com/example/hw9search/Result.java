package com.example.hw9search;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends Activity implements TabListener {
	
	public static Context appContext;
	JSONObject results;
	public String jsonobj;
	String street;
	String City;
	String State;
	String Zipcode;
	String address;
	String homedetails;
	String propertytype;
	String YearBuilt;
	String LotSize;
	String FinishedArea;
	String Bathrooms;
	String Bedrooms;
	String TaxAssessment;
	String TaxAssessmentyear;
	String LastSoldPrice;
	String LastSoldDate;
	String propertyEstimate;
	String propertyEstimateDate;
	String property_range_low;
	String property_range_high;
	String OverallChangeSign;
	String Overallchange;
	String PropertyRange;
	String rentvalue;
	String rentvaluationdate;
	String rentchangesign;
	String rentchange;
	String rent_range_low;
	String rent_range_high;
	String rentrange;
	String oneyear;
	String fiveyear;
	String tenyear; 
	Bitmap myBitmap;
	Bitmap oneyear_bitmap;
	Bitmap fiveyear_bitmap;
	Bitmap tenyear_bitmap;
	int counter=0;
	
	List<Fragment> fragList = new ArrayList<Fragment>();
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new
				 StrictMode.ThreadPolicy.Builder().permitAll().build();
				        StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_result);
		Intent intent=getIntent();
		 jsonobj=intent.getStringExtra("JSON_Object");
		 try {
				JSONObject result=new JSONObject(jsonobj);
				street=result.getJSONObject("result").getString("street");
				City=result.getJSONObject("result").getString("city");
				State=result.getJSONObject("result").getString("state");
				Zipcode=result.getJSONObject("result").getString("zipcode");
				address=street + ", " + City + ", " + State + "-" + Zipcode + " ";
				homedetails=result.getJSONObject("result").getString("homedetails");
				TextView tv1=(TextView)findViewById(R.id.homedetails);
				tv1.setText(Html.fromHtml("<a href=\""+homedetails+"\">"+address+"</a>"));
				tv1.setMovementMethod(LinkMovementMethod.getInstance());
				propertytype=result.getJSONObject("result").getString("useCode");
				TextView tv2=(TextView)findViewById(R.id.Propertytype);
				tv2.setText(propertytype);
				YearBuilt=result.getJSONObject("result").getString("yearBuilt");
				TextView tv3=(TextView)findViewById(R.id.yearbuilt);
				tv3.setText(YearBuilt);
				LotSize=result.getJSONObject("result").getString("lotSizeSqFt");
				TextView tv4=(TextView)findViewById(R.id.lotsize);
				tv4.setText(LotSize);
				FinishedArea=result.getJSONObject("result").getString("finishedSqFt");
				TextView tv5=(TextView)findViewById(R.id.finishedarea);
				tv5.setText(FinishedArea);
				Bathrooms=result.getJSONObject("result").getString("bathrooms");
				TextView tv6=(TextView)findViewById(R.id.bathroom);
				tv6.setText(Bathrooms);
				Bedrooms=result.getJSONObject("result").getString("bedrooms");
				TextView tv7=(TextView)findViewById(R.id.bedrooms);
				tv7.setText(Bedrooms);
				TaxAssessment=result.getJSONObject("result").getString("taxAssessment");
				TextView tv8=(TextView)findViewById(R.id.tax);
				tv8.setText(TaxAssessment);
				TaxAssessmentyear=result.getJSONObject("result").getString("taxAssessmentYear");
				TextView tv9=(TextView)findViewById(R.id.taxyear);
				tv9.setText(TaxAssessmentyear);
				LastSoldPrice=result.getJSONObject("result").getString("lastSoldPrice");
				TextView tv10=(TextView)findViewById(R.id.lastsoldprice);
				tv10.setText(LastSoldPrice);
				LastSoldDate=result.getJSONObject("result").getString("lastSoldDate");
				TextView tv11=(TextView)findViewById(R.id.lastsolddate);
				tv11.setText(LastSoldDate);
				propertyEstimate=result.getJSONObject("result").getString("estimateAmount");
				TextView tv12=(TextView)findViewById(R.id.propestimate);
				tv12.setText(propertyEstimate);
				propertyEstimateDate=result.getJSONObject("result").getString("estimateLastUpdate");
				if(propertyEstimateDate.equals("31-Dec-1969")||propertyEstimateDate.equals("N/A"))
				{
					TextView tv19=(TextView)findViewById(R.id.propdate);
					tv19.setText(Html.fromHtml("Zestimate<sup>&reg;</sup> Property Estimate as of : "));
					
					
				}
				else
				{

					TextView tv19=(TextView)findViewById(R.id.propdate);
					tv19.setText(Html.fromHtml("Zestimate<sup>&reg;</sup> Property Estimate as of "+propertyEstimateDate+ ": "));
				}
				property_range_low=result.getJSONObject("result").getString("estimateValuationRangeLow");
				property_range_high=result.getJSONObject("result").getString("estimateValuationRangeHigh");
				if(!property_range_low.contentEquals("N/A")&&!property_range_low.contentEquals("N/A"))
				{
					PropertyRange="$"+property_range_low+"-$"+property_range_high;
				}
				else
				{
					PropertyRange="N/A";
				}
				TextView tv13=(TextView)findViewById(R.id.proprange);
				tv13.setText(PropertyRange);
				rentvalue=result.getJSONObject("result").getString("restimateAmount");
				TextView tv14=(TextView)findViewById(R.id.rentvalue);
				tv14.setText(rentvalue);
				rentvaluationdate=result.getJSONObject("result").getString("restimateLastUpdate");
				if(rentvaluationdate.equals("31-Dec-1969")||rentvaluationdate.equals("N/A"))
				{
					TextView tv20=(TextView)findViewById(R.id.rentzestimatedate);
					tv20.setText(Html.fromHtml("Zestimate<sup>&reg;</sup> Rent Estimate as of : "));
					
					
				}
				else
				{

					TextView tv20=(TextView)findViewById(R.id.rentzestimatedate);
					tv20.setText(Html.fromHtml("Zestimate<sup>&reg;</sup> Rent Estimate as of "+rentvaluationdate+ ": "));
				}
				rent_range_low=result.getJSONObject("result").getString("estimateValuationRangeLow");
				rent_range_high=result.getJSONObject("result").getString("estimateValuationRangeHigh");
				if(!rent_range_low.contentEquals("N/A")&&!rent_range_high.contentEquals("N/A"))
				{
					rentrange="$"+rent_range_low+"-$"+rent_range_high;
				}
				else
				{
					rentrange="N/A";
				}
				TextView tv15=(TextView)findViewById(R.id.rentrange);
				tv15.setText(rentrange);
				Overallchange=result.getJSONObject("result").getString("estimateValueChange");
				OverallChangeSign=result.getJSONObject("result").getString("estimateValueChangeSign");
				if(OverallChangeSign.equals("+"))
				{
					if(!Overallchange.equals("N/A"))
					{
						TextView tv17=(TextView)findViewById(R.id.overallchange);
						tv17.setText(Overallchange);
						Drawable draw = getResources().getDrawable(R.drawable.up);
						tv17.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
					}
					else
					{
						TextView tv17=(TextView)findViewById(R.id.overallchange);
						tv17.setText(Overallchange);	
					}
				}
				else
				{
					if(!Overallchange.equals("N/A"))
					{
						TextView tv17=(TextView)findViewById(R.id.overallchange);
						tv17.setText(Overallchange);
						Drawable draw = getResources().getDrawable(R.drawable.down);
						tv17.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
					}
					else
					{
						TextView tv17=(TextView)findViewById(R.id.overallchange);
						tv17.setText(Overallchange);
					}
					
				}
				rentchange=result.getJSONObject("result").getString("restimateValueChange");
				rentchangesign=result.getJSONObject("result").getString("restimateValueChangeSign");
				if(rentchangesign.equals("+"))
				{
					if(!rentchange.equals("N/A"))
					{
						TextView tv18=(TextView)findViewById(R.id.rentchange);
						tv18.setText(rentchange);
						Drawable draw = getResources().getDrawable(R.drawable.up);
						tv18.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
					}
					else
					{
						TextView tv18=(TextView)findViewById(R.id.rentchange);
						tv18.setText(rentchange);
					}
				}
				else
				{
					if(!rentchange.equals("N/A"))
					{
						TextView tv18=(TextView)findViewById(R.id.rentchange);
						tv18.setText(rentchange);
						Drawable draw = getResources().getDrawable(R.drawable.down);
						tv18.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
					}
					else
					{
						TextView tv18=(TextView)findViewById(R.id.rentchange);
						tv18.setText(rentchange);
					}
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TextView zil=(TextView)findViewById(R.id.zillowbranding);
			
			zil.setText(Html.fromHtml("&copy; Zillow, Inc., 2006-2014. Use is subject to <a href=\"http://www.zillow.com/corp/Terms.htm\">Terms of Use</a><br></br><a href=\"http://www.zillow.com/zestimate/\">What's a Zestimate?</a>"));
			zil.setMovementMethod(LinkMovementMethod.getInstance());
			//TextView tv = (TextView) v.findViewById(R.id.text);
			//tv.setText("Fragment " + (index ));
		try {
			 results= new JSONObject(jsonobj);
			 TextView tv = (TextView) findViewById(R.id.heading);
				try {
					JSONObject result=new JSONObject(jsonobj);
					street=result.getJSONObject("result").getString("street");
					City=result.getJSONObject("result").getString("city");
					State=result.getJSONObject("result").getString("state");
					Zipcode=result.getJSONObject("result").getString("zipcode");
					address=street + ", " + City + ", " + State + "-" + Zipcode + " ";
					oneyear=result.getJSONObject("result").getString("chart1year");
					fiveyear=result.getJSONObject("result").getString("chart5year");
					tenyear=result.getJSONObject("result").getString("chart10year");
				
					try {
						oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
						fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
						tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
						ImageView img = (ImageView)findViewById(R.id.image);
						img.setImageBitmap(oneyear_bitmap);	
						counter++;
					} catch (MalformedURLException e) {
						 //TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				Button previous=(Button)findViewById(R.id.previous);
				String x="<p><b>Historical Zestimate for the past 1 year</b><br></br>"+address+"</p>";
				tv.setText(Html.fromHtml(x));
				//tv.setText(oneyear);
				TextView zil1=(TextView)findViewById(R.id.zillowbranding2);
				
				zil1.setText(Html.fromHtml("&copy; Zillow, Inc., 2006-2014. Use is subject to <a href=\"http://www.zillow.com/corp/Terms.htm\">Terms of Use</a><br></br><a href=\"http://www.zillow.com/zestimate/\">What's a Zestimate?</a>"));
				zil1.setMovementMethod(LinkMovementMethod.getInstance());
			
			//home.setText(address);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab basicinfo = actionbar.newTab().setText("Basic Info");
        ActionBar.Tab history = actionbar.newTab().setText("Historical Zestimate");



    //set the Tab listener. Now we can listen for clicks.
        basicinfo.setTabListener(this);
        history.setTabListener(this);

   //add the two tabs to the actionbar
        actionbar.addTab(basicinfo);
        actionbar.addTab(history);
        LinearLayout basic=(LinearLayout)findViewById(R.id.basicinfo);
        basic.setVisibility(View.VISIBLE);
        LinearLayout history1=(LinearLayout)findViewById(R.id.history);
        history1.setVisibility(View.GONE);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void onTabSelected( Tab tab, FragmentTransaction ft) {
	
		if(tab.getText().equals("Basic Info"))
		{
			LinearLayout basic=(LinearLayout)findViewById(R.id.basicinfo);
			LinearLayout history=(LinearLayout)findViewById(R.id.history);
			basic.setVisibility(View.VISIBLE);
			history.setVisibility(View.GONE);
		}
		else
			
		{
			LinearLayout basic=(LinearLayout)findViewById(R.id.basicinfo);
			LinearLayout history=(LinearLayout)findViewById(R.id.history);
			basic.setVisibility(View.GONE);
			history.setVisibility(View.VISIBLE);
		}
		
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTabUnselected( Tab tab, FragmentTransaction ft) {
		
		
	}

public void next(View v){
	if(counter==0)
	{
		TextView tv = (TextView) findViewById(R.id.heading);
		String x="<p><b>Historical Zestimate for the past 1 year</b><br></br>"+address+"</p>";
		tv.setText(Html.fromHtml(x));
		try {
			//oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
			fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
			//tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
			ImageView img = (ImageView)findViewById(R.id.image);
			img.setImageBitmap(oneyear_bitmap);
			counter=1;
		} catch (MalformedURLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	else if(counter==1)
	{
		TextView tv = (TextView) findViewById(R.id.heading);
		String x="<p><b>Historical Zestimate for the past 5 year</b><br></br>"+address+"</p>";
		tv.setText(Html.fromHtml(x));try {
			//oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
			fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
			//tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
			ImageView img = (ImageView)findViewById(R.id.image);
			img.setImageBitmap(fiveyear_bitmap);
			counter=2;
		} catch (MalformedURLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
	else if(counter==2)
	{
		TextView tv = (TextView) findViewById(R.id.heading);
		String x="<p><b>Historical Zestimate for the past 10 year</b><br></br>"+address+"</p>";
		tv.setText(Html.fromHtml(x));
		try {
			//oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
			//fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
			tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
			ImageView img = (ImageView)findViewById(R.id.image);
			img.setImageBitmap(tenyear_bitmap);
			counter=0;
		} catch (MalformedURLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
}
}
public void previous(View v)
{
	if(counter==0)
	{
		TextView tv = (TextView) findViewById(R.id.heading);
		String x="<p><b>Historical Zestimate for the past 1 year</b><br></br>"+address+"</p>";
		tv.setText(Html.fromHtml(x));
		try {
			oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
			//fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
			//tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
			ImageView img = (ImageView)findViewById(R.id.image);
			img.setImageBitmap(oneyear_bitmap);
			
		} catch (MalformedURLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
		counter=1;
		
	}
	else if(counter==1)
	{
		TextView tv = (TextView) findViewById(R.id.heading);
		String x="<p><b>Historical Zestimate for the past 5 year</b><br></br>"+address+"</p>";
		tv.setText(Html.fromHtml(x));	
		try {
			//oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
			fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
			//tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
			ImageView img = (ImageView)findViewById(R.id.image);
			img.setImageBitmap(fiveyear_bitmap);
			
		} catch (MalformedURLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
		counter=2;
	}
	else
	{
		TextView tv = (TextView) findViewById(R.id.heading);
		String x="<p><b>Historical Zestimate for the past 10 year</b><br></br>"+address+"</p>";
		tv.setText(Html.fromHtml(x));	try {
			//oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
			//fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
			tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
			ImageView img = (ImageView)findViewById(R.id.image);
			img.setImageBitmap(tenyear_bitmap);
			
		} catch (MalformedURLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
		counter=0;	
	}
	
}
public void facebook_click(View v)
{
	Intent intent = new Intent(getBaseContext(),post.class);
	intent.putExtra("address", address.toString());
	intent.putExtra("homedetails",homedetails.toString());
	intent.putExtra("price",LastSoldPrice.toString());
	intent.putExtra("change",Overallchange);
	intent.putExtra("img",oneyear);
	
	startActivity(intent);
}
	
}

