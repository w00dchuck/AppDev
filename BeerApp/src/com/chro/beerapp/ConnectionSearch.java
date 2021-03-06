package com.chro.beerapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

public class ConnectionSearch extends AsyncTask<String, String, String>{
	
	
	Context context;
	ProgressDialog dialog;
	Class<?> cls;
	float longt=0.0f;
	float lalt=0.0f;
	ConnectionSearch(Context context,ProgressDialog dialog,Class <?> cls)
	{
		this.cls = cls;
		this.context = context;
		this.dialog = dialog;
	}
	public String Search(int Categorie,float latiude,float longitude,int Radius) throws ClientProtocolException, IOException
	{
		return "";
		
	} 
/*
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = httpclient.execute(new HttpGet("http://www.google.com"));
	    StatusLine statusLine = response.getStatusLine();
	    String responseString;
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        responseString = out.toString();
	        out.close();
	        //..more logic
	    } else{
	        //Closes the connection.
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }
		return responseString;
	}*/
	public List<String> Parse()
	{
		return null;
	}
	@Override
	protected String doInBackground(String... params) {
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();

	    String responseStr = "";
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        longt = Float.valueOf(params[1]);
	        lalt = Float.valueOf(params[2]);
	        //nameValuePairs.add(new BasicNameValuePair("description", params[0]));
	        nameValuePairs.add(new BasicNameValuePair("longtitude", params[1]));
	        nameValuePairs.add(new BasicNameValuePair("latitude", params[2]));
	        nameValuePairs.add(new BasicNameValuePair("radius", params[3]));
	        if(!params[4].equals(""))
	        {
	        	nameValuePairs.add(new BasicNameValuePair("categorie", params[4]));
	        }
	        if(!params[5].equals(""))
	        {
		        nameValuePairs.add(new BasicNameValuePair("productName", params[5]));

	        }
	    	String req = URLEncodedUtils.format(nameValuePairs, "utf-8");
		    HttpGet httpget = new HttpGet("http://141.30.224.219:5000?"+ req);
	       // httpget.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httpget);
	        responseStr = EntityUtils.toString(response.getEntity());
		    publishProgress(responseStr);
	    } catch (ClientProtocolException e) {
	    	Log.i(e.toString(),e.toString());
	    	return "";
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	       Log.i(e.toString(),"execpt"); // TODO Auto-generated catch block
	       return "";
	    }

	    return responseStr;
	}
	@Override
	protected void onPostExecute(String responseStr)
	{
		Entries e = Entries.getInstance();
		JSONObject j;
		JSONArray array;

		dialog.cancel();
		if(responseStr.equals(""))
		{
	    	CharSequence text = "Error connecting please try again later";
	    	Toast t = Toast.makeText(context,text,Toast.LENGTH_LONG);
	    	t.show();
			SharedPreferences prefs = context.getSharedPreferences("location", context.MODE_PRIVATE);
			float Longitude = prefs.getFloat("Longitude", 0);
			float Latitude = prefs.getFloat("Latitude", 0);
			float R = 6371.0f; // Radius of the earth in km
			float dLat = (Latitude-lalt);  // deg2rad below
			float dLon = (Longitude-longt); 
			double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			    Math.cos(lalt) * Math.cos(Latitude) * 
			    Math.sin(dLon/2) * Math.sin(dLon/2)
			    ; 
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
			double d = R * c; // Distance in km
			if(d > 1)
				e.Del();
			Calendar cal = Calendar.getInstance();

			int h = prefs.getInt("hour", 24);
			if(h+1 < cal.get(Calendar.HOUR_OF_DAY))
				e.Del();
			SharedPreferences.Editor editor = prefs.edit();
			editor.commit();

			Intent i = new Intent(context, cls); 
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			context.startActivity(i); 
			return ;
		}
		try {
			responseStr = Html.fromHtml(responseStr).toString();
			array = new JSONArray(responseStr);
			//if(array.length() > 0)
				e.Del();
			for(int n = 0; n < array.length();n++)
			{
				j = array.getJSONObject(n);
				e.addEntrie(j.getInt("id"), j.getString("categorie"), j.getString("productName"), (float)(j.getDouble("price")), j.getInt("quantity"), j.getString("contactDetails"), (float)j.getDouble("latitude"), (float)(j.getDouble("longtitude")), j.getString("beginTime"),j.getString("endTime"),j.getInt("user_id"),j.getBoolean("active"),j.getBoolean("retry"));

			}
			SharedPreferences prefs = context.getSharedPreferences("location", context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			Calendar cal = Calendar.getInstance();
			editor.putInt("hour", cal.get(Calendar.HOUR_OF_DAY));
			editor.commit();
			Intent i = new Intent(context, cls); 
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			context.startActivity(i); 
			//context.startActivity(new Intent(context, EntryActivity.class));
			return;
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	    	CharSequence text = "Something somewhere went terribly wrong";
	    	Toast t = Toast.makeText(context,text,Toast.LENGTH_LONG);
	    	t.show();
			return ;
		}
		//parse response string
		
	}
	protected void onProgressUpdate()
	{
	}
}
