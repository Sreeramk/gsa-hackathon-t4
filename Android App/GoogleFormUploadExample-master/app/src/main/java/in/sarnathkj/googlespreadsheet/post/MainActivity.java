package in.sarnathkj.googlespreadsheet.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class MainActivity extends Activity {
	
	final String myTag = "DocsUpload";

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(myTag, "OnCreate()");
        mainListView = (ListView) findViewById( R.id.listview );
// Create and populate a List of planet names.
        String[] planets = new String[] { };
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
//        listAdapter.add( "The only area of dissatisfaction is with the restrooms.  When there is an issue with one of the toilets - it is not fixed immediately.  The restrooms do not smell bad in the mornings but often smell bad throughout the afternoon.  I understand how difficult it must be to keep the restrooms clean with people trashing them all day." );
//        listAdapter.add( "The never-ending cubicle style is no conducive to working." );
//        listAdapter.add( "Ladies bathroom need air fresher in them at all time for freshness they will bring 1 time to refill the freshner then " );
//

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );
        //mainListView.setVisibility(View.INVISIBLE);
        Button send = (Button) findViewById(R.id.check_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdapter.add("Issues Near Your Location:");
                listAdapter.add( "The only area of dissatisfaction is with the restrooms.  When there is an issue with one of the toilets - it is not fixed immediately.  The restrooms do not smell bad in the mornings but often smell bad throughout the afternoon.  I understand how difficult it must be to keep the restrooms clean with people trashing them all day." );
                listAdapter.add( "The never-ending cubicle style is no conducive to working." );
                listAdapter.add( "Ladies bathroom need air fresher in them at all time for freshness they will bring 1 time to refill the freshner then " );


            }
        });

    }

    public void callPostData(View view) {
        Log.i(myTag, "Calling Post Data");
        //Creating a thread since network actions handled in the background.
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                postData();

            }
        });
        t.start();
    }

//    public void listIssues() {
//        listAdapter.add( "The only area of dissatisfaction is with the restrooms.  When there is an issue with one of the toilets - it is not fixed immediately.  The restrooms do not smell bad in the mornings but often smell bad throughout the afternoon.  I understand how difficult it must be to keep the restrooms clean with people trashing them all day." );
//        listAdapter.add( "The never-ending cubicle style is no conducive to working." );
//        listAdapter.add( "Ladies bathroom need air fresher in them at all time for freshness they will bring 1 time to refill the freshner then " );
//
//
//    }

	public void postData() {
//        String fullUrl = "https://docs.google.com/forms/d/1Xj1tQCFJj74oj3N8RzBaIHkGdZFsahmH-_8P3VDuzos/formResponse";
//
//		//String fullUrl = "https://docs.google.com/forms/d/1yd8oQb1TMtNDYYwkqgV78wTu8Fd9P3OGJSa1MCJoE9U/formResponse";
//		HttpRequest mReq = new HttpRequest();
//
        //Getting Sensor Value
        EditText sensorValue = (EditText) findViewById(R.id.sensor_value);
        EditText bathroomValue = (EditText) findViewById(R.id.Bathroom_value);
        EditText ACValue = (EditText) findViewById(R.id.AC_value);

        String finalBathroomValue = bathroomValue.getText().toString();
        String finalACValue = ACValue.getText().toString();
		String finalSensorValue = sensorValue.getText().toString();
//
//		String data = "entry.75015930=" + URLEncoder.encode(finalSensorValue);
//        Log.i(myTag, "TestData-"+data);
//		String response = mReq.sendPost(fullUrl, data);



//            HttpClient client = new DefaultHttpClient();
//            HttpPost post = new HttpPost("https://spreadsheets.google.com/spreadsheet/formResponse?hl=en_US&formkey=1Ly_u6yHa7sLDIii-ibl2Y9tiwuFOQ2_g7ynj-r4O0I4");
//
//            List<BasicNameValuePair> results = new ArrayList<BasicNameValuePair>();
//            results.add(new BasicNameValuePair("entry.0.single", "SensorData"));
//            results.add(new BasicNameValuePair("entry.1.single", "stuff"));
//            results.add(new BasicNameValuePair("entry.2.single", "cardOneURL"));
//
//            try {
//                post.setEntity(new UrlEncodedFormEntity(results));
//            } catch (UnsupportedEncodingException e) {
//                // Auto-generated catch block
//                Log.e("YOUR_TAG", "An error has occurred", e);
//            }
//            try {
//                client.execute(post);
//            } catch (ClientProtocolException e) {
//                // Auto-generated catch block
//                Log.e("YOUR_TAG", "client protocol exception", e);
//            } catch (IOException e) {
//                // Auto-generated catch block
//                Log.e("YOUR_TAG", "io exception", e);
//            }
        String postBody="";
        String subject ="hello";

        //URL derived from form URL
      String URL="https://docs.google.com/forms/d/1Xj1tQCFJj74oj3N8RzBaIHkGdZFsahmH-_8P3VDuzos/formResponse";
        //input element ids found from the live form page
     String Bathroom_Key="entry_840235746";
     String Building_Key="entry_1899545101";
     String ACHeating_Key="entry_1899545101";
     String SUBJECT_KEY="entry_1682795074";


        postBody =  Bathroom_Key+"=" + finalBathroomValue +
                "&" + SUBJECT_KEY + "=" + finalSensorValue +
                "&" + ACHeating_Key + "=" + finalACValue;
        try {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.sendPost(URL, postBody);
        }catch (Exception exception){
            Log.e("YOUR_TAG", "client protocol exception", exception);
        }
    } }
