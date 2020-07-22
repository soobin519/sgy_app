package com.example.myapplication.util.network;

import android.util.Log;


import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CommunicationUtil {

//	private static final String root_url_goorm = "https://nids-spring-psdg.run.goorm.io";
//	private static final String root_url_aws = "http://nidsprojtestapp.372fabauwi.us-east-1.elasticbeanstalk.com";
//	private static final String server_url = root_url_goorm;
//
//	private boolean stop_flag = false;
//	Thread receiver_t;
//	private String str_response = "";
//	String auth = "";
//
//	private NetworkCallBackInterface callback_Instance;
//
//	public CommunicationUtil(NetworkCallBackInterface callback_Instance) {
//		this.callback_Instance = callback_Instance;
//	}
//
//
//
//	public void signUp(String id, String pw, String name, int gender, String platform, String bd, String email, String hp)	{
//		Thread t = new Thread(new UserJoin(id, pw, name, gender, platform, bd, email, hp));
//		t.start();
//	}
//
//	public void checkExist(String id) {
//		Thread t = new Thread(new CheckUser(id));
//		t.start();
//	}
//
//	public void signIn(String id, String pw) {
//		Thread t = new Thread(new UserAuth(id, pw));
//		t.start();
//	}
//
//	public void getUser(String id) {
//		Thread t = new Thread(new UserResult(id));
//		t.start();
//	}
//
//
//	public class UserJoin implements Runnable {
//		String id;
//		String pw;
//		String name;
//		int gender;
//		String platform;
//		String bd;
//		String email;
//		String hp;
//
//		UserJoin(String id, String pw, String name, int gender, String platform, String bd, String email, String hp) {
//			this.id = id;
//			this.pw = pw;
//			this.name = name;
//			this.gender = gender;
//			this.platform = platform;
//			this.bd = bd;
//			this.email = email;
//			this.hp = hp;
//		}
//
//		@Override
//		public void run() {
//			try {
//				HttpClient httpclient = new DefaultHttpClient();//HttpClientBuilder.create().build();
//				httpclient.getParams().setParameter("http.protocol.expect-continue", false);
//				httpclient.getParams().setParameter("http.connection.timeout", 5000);
//				httpclient.getParams().setParameter("http.socket.timeout", 5000);
//
//				HttpPost httppost = new HttpPost(server_url + "/UserUtil");
//				try {
//					List<NameValuePair> nameValuePairs = new ArrayList<>(10);
//					nameValuePairs.add(new BasicNameValuePair("type", "Register"));
//					nameValuePairs.add(new BasicNameValuePair("id", this.id));
//					nameValuePairs.add(new BasicNameValuePair("pw", this.pw));
//					nameValuePairs.add(new BasicNameValuePair("name", this.name));
//					nameValuePairs.add(new BasicNameValuePair("gender", Integer.toString(this.gender)));
//					nameValuePairs.add(new BasicNameValuePair("platform", this.platform));
//					nameValuePairs.add(new BasicNameValuePair("bd", this.bd));
//					nameValuePairs.add(new BasicNameValuePair("email", this.email));
//					nameValuePairs.add(new BasicNameValuePair("hp", this.hp));
//					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
//					// Execute HTTP Post Request
//					HttpResponse response = httpclient.execute(httppost);
//					HttpEntity entity = response.getEntity();
//					str_response = EntityUtils.toString(entity);
//
//					System.out.println(str_response);
//
//					JsonParser parser = new JsonParser();
//					JsonElement element = parser.parse(str_response);
//					JsonObject jsonObj = element.getAsJsonObject();
//
//					boolean post_insert = jsonObj.get("insert").getAsBoolean();
//					String result = jsonObj.get("result").getAsString();
//
//					System.out.println("post insert : " + post_insert);
//
//					joincallback_Instance.signUpResult(post_insert, result,null);
//				} catch (ClientProtocolException e) {
//					e.printStackTrace();
//					joincallback_Instance.signUpResult(false, "500", "ClientProtocolException");
//				} catch (IOException e) {
//					e.printStackTrace();
//					joincallback_Instance.signUpResult(false, "500", "IOException");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				joincallback_Instance.signUpResult(false, "500", "httpClientException");
//			}
//
//		}
//	}
//
//	public class CheckUser implements Runnable {
//		String id;
//
//		CheckUser(String id) {
//			this.id = id;
//		}
//
//		@Override
//		public void run() {
//			try {
//				HttpClient httpclient = new DefaultHttpClient();//HttpClientBuilder.create().build();
//				httpclient.getParams().setParameter("http.protocol.expect-continue", false);
//				httpclient.getParams().setParameter("http.connection.timeout", 5000);
//				httpclient.getParams().setParameter("http.socket.timeout", 5000);
//
//				HttpPost httppost = new HttpPost(server_url + "/UserUtil");
//				try {
//					// Add your data
//					List<NameValuePair> nameValuePairs = new ArrayList<>(3);
//					nameValuePairs.add(new BasicNameValuePair("type", "check_exist"));
//					nameValuePairs.add(new BasicNameValuePair("id", this.id));
//
//					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//					// Execute HTTP Post Request
//					HttpResponse response = httpclient.execute(httppost);
//					HttpEntity entity = response.getEntity();
//					str_response = EntityUtils.toString(entity);
//
//					System.out.println(str_response);
//
//					JsonParser parser = new JsonParser();
//					JsonElement element = parser.parse(str_response);
//					JsonObject jsonObj = element.getAsJsonObject();
//
//					boolean exist = jsonObj.get("exist").getAsBoolean();
//					String result = jsonObj.get("result").getAsString();
//
//					joincallback_Instance.existResult(result, exist);
//				} catch (ClientProtocolException e) {
//					e.printStackTrace();
//					joincallback_Instance.existResult("error", true);
//				} catch (IOException e) {
//					e.printStackTrace();
//					joincallback_Instance.existResult("error", true);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				joincallback_Instance.existResult("error", true);
//			}
//		}
//	}
//
//
//	public class UserAuth implements Runnable {
//		String id;
//		String pw;
//
//		UserAuth(String id, String pw) {
//			this.id = id;
//			this.pw = pw;
//			Log.d("Auth", this.id);
//			Log.d("Auth", this.pw);
//		}
//
//		@Override
//		public void run() {
//			try {
//
//				HttpClient httpclient = new DefaultHttpClient();//HttpClientBuilder.create().build();
//				httpclient.getParams().setParameter("http.protocol.expect-continue", false);
//				httpclient.getParams().setParameter("http.connection.timeout", 5000);
//				httpclient.getParams().setParameter("http.socket.timeout", 5000);
//
//				HttpPost httppost = new HttpPost(server_url + "/UserUtil");
//				try {
//					// Add your data
//					List<NameValuePair> nameValuePairs = new ArrayList<>(3);
//					nameValuePairs.add(new BasicNameValuePair("type", "signin"));
//					nameValuePairs.add(new BasicNameValuePair("id", this.id));
//					nameValuePairs.add(new BasicNameValuePair("pw", this.pw));
//
//					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//					// Execute HTTP Post Request
//					HttpResponse response = httpclient.execute(httppost);
//					HttpEntity entity = response.getEntity();
//					str_response = EntityUtils.toString(entity);
//
//					System.out.println(str_response);
//
//
//					JsonParser parser = new JsonParser();
//					JsonElement element = parser.parse(str_response);
//					JsonObject jsonObj = element.getAsJsonObject();
//
//
//					boolean post_result = jsonObj.get("result").getAsBoolean();
//					String message = jsonObj.get("message").getAsString();
//
//
//					Gson gson = new Gson();
//					VOUser user_info = gson.fromJson(jsonObj.get("user_info").toString(), VOUser.class);
//
//
//					Log.d("user info", user_info.getName());
//
//					System.out.println("post result : " + post_result);
//					callback_Instance.signInResult(post_result, message, user_info);
//				} catch (ClientProtocolException e) {
//					e.printStackTrace();
//					callback_Instance.signInResult(false, "Connection Error", null);
//				} catch (IOException e) {
//					e.printStackTrace();
//					callback_Instance.signInResult(false, "Connection Error", null);
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				callback_Instance.signInResult(false, "Connection Error", null);
//			}
//		}
//	}
//	//id로 DB에 있는 사용자 정보 받아오기
//	public class UserResult implements Runnable {
//		String id;
//
//		UserResult(String id) {
//			this.id = id;
//			Log.d("Auth", this.id);
//		}
//
//		@Override
//		public void run() {
//			try {
//
//				HttpClient httpclient = new DefaultHttpClient();//HttpClientBuilder.create().build();
//				httpclient.getParams().setParameter("http.protocol.expect-continue", false);
//				httpclient.getParams().setParameter("http.connection.timeout", 5000);
//				httpclient.getParams().setParameter("http.socket.timeout", 5000);
//
//				HttpPost httppost = new HttpPost(server_url + "/UserUtil");
//				try {
//					// Add your data
//					List<NameValuePair> nameValuePairs = new ArrayList<>(3);
//					nameValuePairs.add(new BasicNameValuePair("type", "getUser"));
//					nameValuePairs.add(new BasicNameValuePair("id", this.id));
//
//					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//					// Execute HTTP Post Request
//					HttpResponse response = httpclient.execute(httppost);
//					HttpEntity entity = response.getEntity();
//					str_response = EntityUtils.toString(entity);
//
//					System.out.println(str_response);
//
//
//					JsonParser parser = new JsonParser();
//					JsonElement element = parser.parse(str_response);
//					JsonObject jsonObj = element.getAsJsonObject();
//
//
//					boolean post_result = jsonObj.get("result").getAsBoolean();
//					String message = jsonObj.get("message").getAsString();
//
//
//					Gson gson = new Gson();
//					VOUser user_info = gson.fromJson(jsonObj.get("user_info").toString(), VOUser.class);
//
//
//					Log.d("user info", user_info.getName());
//
//					System.out.println("post result : " + post_result);
//					joincallback_Instance.getUserResult(post_result, message, user_info);
//				} catch (ClientProtocolException e) {
//					e.printStackTrace();
//					joincallback_Instance.getUserResult(false, "Connection Error", null);
//				} catch (IOException e) {
//					e.printStackTrace();
//					joincallback_Instance.getUserResult(false, "Connection Error", null);
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				joincallback_Instance.getUserResult(false, "Connection Error", null);
//			}
//		}
//	}


	}
