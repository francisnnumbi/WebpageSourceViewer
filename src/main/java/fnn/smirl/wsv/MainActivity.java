package fnn.smirl.wsv;

import android.app.*;
import android.os.*;
import com.android.volley.toolbox.*;
import com.android.volley.*;
import android.widget.*;
import android.view.*;
import android.support.v7.app.*;
import okhttp3.*;
import retrofit2.*;
import retrofit2.converter.gson.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
 EditText m_url_et, m_displayer;
 Button m_load_btn;
 ProgressBar m_progress_bar;
 String url="";
 String jUrl = "https://androidtutorialpoint.com/api/RetrofitAndroidObjectResponse";




 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	init();
 }

 private void init() {
	// TODO: Implement this method
	initViews();
	initListeners();
	doRetrifiting();
 }

 private void initViews() {
	// TODO: Implement this method
	m_url_et = (EditText)findViewById(R.id.m_url_et);
	m_displayer = (EditText)findViewById(R.id.m_displayer);
	m_load_btn = (Button)findViewById(R.id.m_load_btn);
	m_progress_bar = (ProgressBar)findViewById(R.id.m_progress_bar);
 }

 private void initListeners() {
	// TODO: Implement this method
	m_load_btn.setOnClickListener(new View.OnClickListener(){

		@Override
		public void onClick(View p1) {
		 // TODO: Implement this method
		 url = m_url_et.getText().toString();
		 m_progress_bar.setVisibility(View.VISIBLE);
		 //uploadUrl();

		}
	 });

 }

 private void doRetrifiting() {
	String API_BASE_URL = "https://api.github.com/";
	OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
	Retrofit.Builder builder = new Retrofit.Builder()
	 .baseUrl(API_BASE_URL)
	 .addConverterFactory(GsonConverterFactory.create());
	Retrofit retrofit = builder.client(httpClient.build())
	 .build();
	GitHubClient client = retrofit.create(GitHubClient.class);

	retrofit2.Call<List<GitHubRepo>> call= client.reposForUser("fs-opensource");
	call.enqueue(new  retrofit2.Callback<List<GitHubRepo>>(){

		@Override
		public void onResponse(retrofit2.Call<List<GitHubRepo>> p1, retrofit2.Response<List<GitHubRepo>> p2) {
		 // TODO: Implement this method
		 m_displayer.setText("hrhrhrt");
		}

		@Override
		public void onFailure(retrofit2.Call<List<GitHubRepo>> p1, Throwable p2) {
		 // TODO: Implement this method
		}
	 });


 }

// public void uploadUrl() {
//	RequestQueue queue = Volley.newRequestQueue(getBaseContext());
//	StringRequest request = new StringRequest(Request.Method.GET, url,
//	 new Response.Listener<String>(){
//
//		@Override
//		public void onResponse(String p1) {
//		 // TODO: Implement this method
//		 m_progress_bar.setVisibility(View.GONE);
//		 m_displayer.setText(organizeText(p1));
//		}
//	 }, new Response.ErrorListener(){
//
//		@Override
//		public void onErrorResponse(VolleyError p1) {
//		 // TODO: Implement this method
//		 m_progress_bar.setVisibility(View.GONE);
//		 m_displayer.setText("ERROR: " + p1.toString());
//		}
//
//	 });
//	queue.add(request);
// }

 private String organizeText(String text) {
	return text.replaceAll("><", ">\n<");
 }
}
