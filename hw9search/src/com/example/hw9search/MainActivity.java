package com.example.hw9search;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.BaseBundle;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText street,city;
	String jsonResult="";
	String val;
	TextView err_street,err_city,err_state;
	Spinner  state;
	Button  search;
	Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        street= (EditText)findViewById(R.id.street_add);
        city=(EditText)findViewById(R.id.city);
        err_street=(TextView)findViewById(R.id.err_street);
        err_city=(TextView)findViewById(R.id.err_city);
        err_state=(TextView)findViewById(R.id.err_state);
        state=(Spinner)findViewById(R.id.state);
        search=(Button)findViewById(R.id.search);
        context=this;
        getWindow().setSoftInputMode(
        	    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        	);
    search.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String Address = "";
	        String City = "";
	        String State = "";
	        boolean isvalidadd = true;
	        boolean isvalidcit = true;
	        boolean isvalidstat = true;
	        TextView tv=(TextView)findViewById(R.id.addressnotfound);
    		tv.setText("");
	        if(street.getText().toString().isEmpty() )
	        {
	            err_street.setText("This field is required*");
	            isvalidadd=false;
	        }
	        else
	        {
	        	err_street.setText("");
	            isvalidadd=true;
	            Address=street.getText().toString();
	        	
	        }
	        if(city.getText().toString().isEmpty())
	        {
	            err_city.setText("This field is required*");
	            isvalidcit=false;
	        }
	        else
	        {
	        	err_city.setText("");
	            isvalidcit=true;
	            City=city.getText().toString();
	        }
	        if(state.getSelectedItem().toString().equals("Select State"))
	        {
	            err_state.setText("This field is required*");
	            isvalidstat=false;
	        }
	        else
	        {
	        	err_state.setText("");
	            isvalidstat=true;
	            State=state.getSelectedItem().toString();
	        }
	        if(isvalidadd&&isvalidcit&&isvalidstat) {
	        	//Toast.makeText(getBaseContext(), "Button1 clicked." +Address+City+State , Toast.LENGTH_SHORT).show();
	        	new GetJSON().execute(Address,City,State);
	        	//Toast.makeText(getBaseContext() , "JSONResult"+jsonResult, Toast.LENGTH_LONG).show();
	        	//int jsonResult;
	        	
	        	}
			
		}
    });
    
}

class GetJSON extends AsyncTask<String, String, String> {
	private ProgressDialog progressDialog = new ProgressDialog(
			MainActivity.this);
	InputStream inputStream = null;
	String url="http://sangamesh-usc.elasticbeanstalk.com/?StreetAddress=";
	@Override
	protected String doInBackground(String... params) {
		try {
			url=url+URLEncoder.encode(params[0], "UTF-8");
			url=url+"&City=";
			url=url+URLEncoder.encode(params[1], "UTF-8");
			url=url+"&State=";
			url=url+URLEncoder.encode(params[2], "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		StringBuilder stringBuilder = null;
		try {
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			URI website=new URI(url);
			request.setURI(website);
			val=url;
			//post.setEntity(new UrlEncodedFormEntity(postParams));
			HttpResponse httpResponse = defaultHttpClient.execute(request);
			HttpEntity httpEntity = httpResponse.getEntity();

			inputStream = httpEntity.getContent();

			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(inputStream));
			stringBuilder = new StringBuilder();
			String line = null;

			while ((line = bufferReader.readLine()) != null) {
				stringBuilder.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
			stringBuilder
					.append("There is some problem with network. Please check the network connectivity.");
			progressDialog.dismiss();
		}

		return stringBuilder.toString();
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.show();
		progressDialog.setMessage("Loading...");
		progressDialog.setCancelable(true);
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		//jsonResult = result;
		
		try {
			JSONObject results= new JSONObject(result);
			jsonResult=results.getJSONObject("result").getString("code");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		progressDialog.dismiss();
		String seven=new String("789");
    	if(jsonResult.equals(seven))
    	{
    	//Toast.makeText(getBaseContext() , "code"+ code, Toast.LENGTH_LONG).show();
    		TextView tv=(TextView)findViewById(R.id.addressnotfound);
    		tv.setText("No Exact match found!! Please check the address");
    	}
    	else
    	{
    		TextView tv=(TextView)findViewById(R.id.addressnotfound);
    		tv.setText("");
    		Intent intent = new Intent(context,Result.class);
    		intent.putExtra("JSON_Object", result.toString());
    		startActivity(intent);
    		
    	}
	}
}
}
