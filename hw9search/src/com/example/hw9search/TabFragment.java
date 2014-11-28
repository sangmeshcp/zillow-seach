package com.example.hw9search;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class TabFragment extends Fragment  {
private int index;
private String jsonobj="fuck";
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
//private AQuery aq;
Bitmap myBitmap;
Bitmap oneyear_bitmap;
Bitmap fiveyear_bitmap;
Bitmap tenyear_bitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Bundle data = getArguments();
		index = data.getInt("idx");
		jsonobj=data.getString("JSON_Object");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ImageSwitcher imageSwitcher;
		if(index==0)
		{	
		View v = inflater.inflate(R.layout.basincinfo, null);
		try {
			JSONObject result=new JSONObject(jsonobj);
			street=result.getJSONObject("result").getString("street");
			City=result.getJSONObject("result").getString("city");
			State=result.getJSONObject("result").getString("state");
			Zipcode=result.getJSONObject("result").getString("zipcode");
			address=street + ", " + City + ", " + State + "-" + Zipcode + " ";
			homedetails=result.getJSONObject("result").getString("homedetails");
			TextView tv1=(TextView)v.findViewById(R.id.homedetails);
			tv1.setText(Html.fromHtml("<a href=\""+homedetails+"\">"+address+"</a>"));
			tv1.setMovementMethod(LinkMovementMethod.getInstance());
			propertytype=result.getJSONObject("result").getString("useCode");
			TextView tv2=(TextView)v.findViewById(R.id.Propertytype);
			tv2.setText(propertytype);
			YearBuilt=result.getJSONObject("result").getString("yearBuilt");
			TextView tv3=(TextView)v.findViewById(R.id.yearbuilt);
			tv3.setText(YearBuilt);
			LotSize=result.getJSONObject("result").getString("lotSizeSqFt");
			TextView tv4=(TextView)v.findViewById(R.id.lotsize);
			tv4.setText(LotSize);
			FinishedArea=result.getJSONObject("result").getString("finishedSqFt");
			TextView tv5=(TextView)v.findViewById(R.id.finishedarea);
			tv5.setText(FinishedArea);
			Bathrooms=result.getJSONObject("result").getString("bathrooms");
			TextView tv6=(TextView)v.findViewById(R.id.bathroom);
			tv6.setText(Bathrooms);
			Bedrooms=result.getJSONObject("result").getString("bedrooms");
			TextView tv7=(TextView)v.findViewById(R.id.bedrooms);
			tv7.setText(Bedrooms);
			TaxAssessment=result.getJSONObject("result").getString("taxAssessment");
			TextView tv8=(TextView)v.findViewById(R.id.tax);
			tv8.setText(TaxAssessment);
			TaxAssessmentyear=result.getJSONObject("result").getString("taxAssessmentYear");
			TextView tv9=(TextView)v.findViewById(R.id.taxyear);
			tv9.setText(TaxAssessmentyear);
			LastSoldPrice=result.getJSONObject("result").getString("lastSoldPrice");
			TextView tv10=(TextView)v.findViewById(R.id.lastsoldprice);
			tv10.setText(LastSoldPrice);
			LastSoldDate=result.getJSONObject("result").getString("lastSoldDate");
			TextView tv11=(TextView)v.findViewById(R.id.lastsolddate);
			tv11.setText(LastSoldDate);
			propertyEstimate=result.getJSONObject("result").getString("estimateAmount");
			TextView tv12=(TextView)v.findViewById(R.id.propestimate);
			tv12.setText(propertyEstimate);
			propertyEstimateDate=result.getJSONObject("result").getString("estimateLastUpdate");
			if(propertyEstimateDate.equals("31-Dec-1969")||propertyEstimateDate.equals("N/A"))
			{
				TextView tv19=(TextView)v.findViewById(R.id.propdate);
				tv19.setText(Html.fromHtml("Zestimate<sup>&reg;</sup> Property Estimate as of : "));
				
				
			}
			else
			{

				TextView tv19=(TextView)v.findViewById(R.id.propdate);
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
			TextView tv13=(TextView)v.findViewById(R.id.proprange);
			tv13.setText(PropertyRange);
			rentvalue=result.getJSONObject("result").getString("restimateAmount");
			TextView tv14=(TextView)v.findViewById(R.id.rentvalue);
			tv14.setText(rentvalue);
			rentvaluationdate=result.getJSONObject("result").getString("restimateLastUpdate");
			if(rentvaluationdate.equals("31-Dec-1969")||rentvaluationdate.equals("N/A"))
			{
				TextView tv20=(TextView)v.findViewById(R.id.rentzestimatedate);
				tv20.setText(Html.fromHtml("Zestimate<sup>&reg;</sup> Rent Estimate as of : "));
				
				
			}
			else
			{

				TextView tv20=(TextView)v.findViewById(R.id.rentzestimatedate);
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
			TextView tv15=(TextView)v.findViewById(R.id.rentrange);
			tv15.setText(rentrange);
			Overallchange=result.getJSONObject("result").getString("estimateValueChange");
			OverallChangeSign=result.getJSONObject("result").getString("estimateValueChangeSign");
			if(OverallChangeSign.equals("+"))
			{
				if(!Overallchange.equals("N/A"))
				{
					TextView tv17=(TextView)v.findViewById(R.id.overallchange);
					tv17.setText(Overallchange);
					Drawable draw = getResources().getDrawable(R.drawable.up);
					tv17.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
				}
				else
				{
					TextView tv17=(TextView)v.findViewById(R.id.overallchange);
					tv17.setText(Overallchange);	
				}
			}
			else
			{
				if(!Overallchange.equals("N/A"))
				{
					TextView tv17=(TextView)v.findViewById(R.id.overallchange);
					tv17.setText(Overallchange);
					Drawable draw = getResources().getDrawable(R.drawable.down);
					tv17.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
				}
				else
				{
					TextView tv17=(TextView)v.findViewById(R.id.overallchange);
					tv17.setText(Overallchange);
				}
				
			}
			rentchange=result.getJSONObject("result").getString("restimateValueChange");
			rentchangesign=result.getJSONObject("result").getString("restimateValueChangeSign");
			if(rentchangesign.equals("+"))
			{
				if(!rentchange.equals("N/A"))
				{
					TextView tv18=(TextView)v.findViewById(R.id.rentchange);
					tv18.setText(rentchange);
					Drawable draw = getResources().getDrawable(R.drawable.up);
					tv18.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
				}
				else
				{
					TextView tv18=(TextView)v.findViewById(R.id.rentchange);
					tv18.setText(rentchange);
				}
			}
			else
			{
				if(!rentchange.equals("N/A"))
				{
					TextView tv18=(TextView)v.findViewById(R.id.rentchange);
					tv18.setText(rentchange);
					Drawable draw = getResources().getDrawable(R.drawable.down);
					tv18.setCompoundDrawablesWithIntrinsicBounds(null,null,draw,null);
				}
				else
				{
					TextView tv18=(TextView)v.findViewById(R.id.rentchange);
					tv18.setText(rentchange);
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TextView zil=(TextView)v.findViewById(R.id.zillowbranding);
		
		zil.setText(Html.fromHtml("&copy; Zillow, Inc., 2006-2014. Use is subject to <a href=\"http://www.zillow.com/corp/Terms.htm\">Terms of Use</a><br></br><a href=\"http://www.zillow.com/zestimate/\">What's a Zestimate?</a>"));
		zil.setMovementMethod(LinkMovementMethod.getInstance());
		//TextView tv = (TextView) v.findViewById(R.id.text);
		//tv.setText("Fragment " + (index ));
		return v;
		}
		else 
		{
			View v = inflater.inflate(R.layout.historicalzestimate, null);
			TextView tv = (TextView) v.findViewById(R.id.heading);
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
				      ImageView i = (ImageView)v.findViewById(R.id.image);
				      Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
				      i.setImageBitmap(bitmap); 
				    } catch (MalformedURLException e) {
				      e.printStackTrace();
				    } catch (IOException e) {
				      e.printStackTrace();
				    }
				
				//try {
					//oneyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(oneyear).getContent());
				//	fiveyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(fiveyear).getContent());
					//tenyear_bitmap = BitmapFactory.decodeStream((InputStream)new URL(tenyear).getContent());
			//	} //catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//} //catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
				//geturl(oneyear);
				 //oneyear_bitmap=myBitmap;
				
				//geturl(fiveyear);
			//fiveyear_bitmap=myBitmap;
			//	
				//tenyear_bitmap=myBitmap;
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ImageView img = (ImageView)v.findViewById(R.id.image);
			//Drawable drawable =new BitmapDrawable(oneyear_bitmap);
			//img.setImageBitmap(oneyear_bitmap);
			String x="<p><b>Historical Zestimate for the past 1 year</b><br></br>"+address+"</p>";
			tv.setText(Html.fromHtml(x));
			//tv.setText(oneyear);
			TextView zil=(TextView)v.findViewById(R.id.zillowbranding2);
			
			zil.setText(Html.fromHtml("&copy; Zillow, Inc., 2006-2014. Use is subject to <a href=\"http://www.zillow.com/corp/Terms.htm\">Terms of Use</a><br></br><a href=\"http://www.zillow.com/zestimate/\">What's a Zestimate?</a>"));
			zil.setMovementMethod(LinkMovementMethod.getInstance());
			return v;
		}
	}
	/*void geturl(String url) throws MalformedURLException
	{
		
	

			URL newurl=null;
			newurl = new URL(url);
			HttpGet httpRequest = null;
			httpRequest = new HttpGet(newurl.toURI());
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = null;
			response = (HttpResponse) httpclient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = null;
			bufHttpEntity = new BufferedHttpEntity(entity);
			InputStream instream = null;
			instream = bufHttpEntity.getContent();
			myBitmap = BitmapFactory.decodeStream(instream);
		
	
	}
	*/
	private Bitmap fetchImage(String imageURL) {
		try {				
			java.net.URL aws_url = new java.net.URL(imageURL);
			HttpURLConnection connection = (HttpURLConnection) aws_url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			return BitmapFactory.decodeStream(input);
		  }catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}	

