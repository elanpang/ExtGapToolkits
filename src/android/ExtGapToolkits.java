/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package com.dextrys.cordova.extgap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
//

/**
 * This class provides ExtGapToolkits on the device.
 */
public class ExtGapToolkits extends CordovaPlugin {

	public static final String DTAG="ExtGapToolkits---->>";
	
    /**
     * Constructor.
     */
    public ExtGapToolkits() {
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArray of arguments for the plugin.
     * @param callbackContext   The callback context used when calling back into JavaScript.
     * @return                  True when the action was valid, false otherwise.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        PluginResult.Status status = PluginResult.Status.OK;
        String result = "";

        // dirty inject
        if (action.equals("droidNaviTo")) {
			Log.i(DTAG,"droidNaviTo  begin");
            this.droidNaviTo(args.getString(0),args.getString(1));
			Log.i(DTAG,"droidNaviTo  end");
            //callbackContext.sendPluginResult(new PluginResult(status, b));//
            return true;
        }else if (action.equals("haveInstalledBaiduMap")) {
			Log.i(DTAG,"haveInstalledBaiduMap  begin");
			boolean b=this.haveInstalledBaiduMap();
			//			callbackContext.success(b);
			callbackContext.sendPluginResult(new PluginResult(status,b));
			Log.i(DTAG,"haveInstalledBaiduMap  end");
            //callbackContext.sendPluginResult(new PluginResult(status, b));//
            return true;
        }else if (action.equals("naviWithBaiduMap")) {
			Log.i(DTAG,"naviWithBaiduMap  begin");
            this.naviWithBaiduMap(args.getString(0),args.getString(1));
			Log.i(DTAG,"naviWithBaiduMap  end");
            //callbackContext.sendPluginResult(new PluginResult(status, b));//
            return true;
        }else if (action.equals("clipImage")) {
			Log.i(DTAG,"clipImage  begin");
            this.clipImage(args.getString(0),args.getString(1),args.getInt(2),args.getInt(3),args.getInt(4),args.getInt(5));
			Log.i(DTAG,"clipImage  end");
            //callbackContext.sendPluginResult(new PluginResult(status, b));//
            return true;
        }else if (action.equals("resizeImage")) {
			Log.i(DTAG,"resizeImage  begin");
            this.resizeImage(args.getString(0),args.getString(1),args.getInt(2),args.getInt(3));
			Log.i(DTAG,"resizeImage  end");
            //callbackContext.sendPluginResult(new PluginResult(status, b));//
            return true;
        }else if (action.equals("encodeFile2Base64")) {
			Log.i(DTAG,"encodeFile2Base64  begin");
            String b64String=this.encodeFile2Base64(args.getString(0));
            callbackContext.sendPluginResult(new PluginResult(status,b64String));
			Log.i(DTAG,"encodeFile2Base64  end");
            //callbackContext.sendPluginResult(new PluginResult(status, b));//
            return true;
        }else if (action.equals("decodeBase642File")) {
			Log.i(DTAG,"decodeBase642File  begin");
            this.decodeBase642File(args.getString(0),args.getString(1));
			Log.i(DTAG,"decodeBase642File  end");
            //callbackContext.sendPluginResult(new PluginResult(status, b));//
            return true;
        }
        //
        else {
            return false;
        }

//        Unreachable code
        // Only alert and confirm are async.
//        callbackContext.success();
//        return true;
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------







	// dirty inject
	
    public void droidNaviTo(String lat, String lon) {
			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + lat +","+ lon)); 
		    this.cordova.getActivity().startActivity(i);
    }
	
	public boolean haveInstalledBaiduMap() {  
		Context context=this.cordova.getActivity();
	    PackageManager packageManager = context.getPackageManager();  
	    try {  
	        PackageInfo pInfo = packageManager.getPackageInfo("com.baidu.BaiduMap",  
	                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT); 
	        //判断是否获取到了对应的包名信息 
	        if(pInfo!=null){  
	            return true;
	        }  
	    } catch (NameNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    return false;
	} 

		//naviWithBaiduMap(mContext,"31.32312,120.62134","34.264642646862,108.95108518068");
	public void naviWithBaiduMap(String origin,String destination) {  
		Context context=this.cordova.getActivity();
        //移动APP调起Android百度地图方式举例
        Intent intent = null;
		try {
//			intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving&region=西安&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//			intent = Intent.getIntent("intent://map/direction?origin=latlng:31.32312,120.62134|name:起点&destination=latlng:34.264642646862,108.95108518068|name:终点&mode=driving&src=XiaoXun|XiaoXun#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
			intent = Intent.getIntent("intent://map/direction?origin=latlng:"+origin+"|name:起点&destination=latlng:"+destination+"|name:终点&mode=driving&src=XiaoXun|XiaoXun#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		context.startActivity(intent); //启动调用
       //
	}
	
    

		//Uri uri = Uri.parse(src);
	    //String tmpPath1=uri.getPath();
	    //String tmpPath2=uri.toString();
    public void clipImage(String src,String dest,int x,int y,int w,int h) {
	    BitmapFactory.Options opt = new BitmapFactory.Options();
	    opt.inPreferredConfig=Bitmap.Config.RGB_565;//表示16位位图 565代表对应三原色占的位数
	    opt.inInputShareable=true;
	    opt.inPurgeable=true;//设置图片可以被回收
	    opt.inTempStorage = new byte[1024*1024*10];
	    
    	Bitmap bitmap  = BitmapFactory.decodeFile(src,opt);//decodeStream
    	Bitmap newBmp=Bitmap.createBitmap(bitmap, x, y, w, h, null, false);
    	saveBitmap2JPG(dest,newBmp);
    	if(bitmap!=null){
    		bitmap.recycle();
    	}
    }
	
    public void resizeImage(String src,String dest,int sample,int quality) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig=Bitmap.Config.RGB_565;//表示16位位图 565代表对应三原色占的位数
		opt.inInputShareable=true;
		opt.inPurgeable=true;//设置图片可以被回收
		opt.inTempStorage = new byte[1024*1024*10];
		opt.inSampleSize = sample;
	    Bitmap bitmap  = BitmapFactory.decodeFile(src,opt);//decodeStream
	    Bitmap newBmp=Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), null, false);
	    saveBitmap2JPG(dest,newBmp,quality);
    	if(bitmap!=null){
    		bitmap.recycle();
    	}
    }
    
    
	public void saveBitmap2JPG(String path,Bitmap bitmap){
		  File f = new File(path);//("/sdcard/" + bitName + ".png");
		  FileOutputStream fOut = null;
		  try {
			   f.createNewFile();
			   fOut = new FileOutputStream(f);
			   bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fOut);
			   fOut.flush();
			   fOut.close();
		  }catch (FileNotFoundException e) {
			  e.printStackTrace();
		  }catch (IOException e) {
			  e.printStackTrace();
		  }
	}
	
	public void saveBitmap2JPG(String path,Bitmap bitmap,int quality){
		  File f = new File(path);//("/sdcard/" + bitName + ".png");
		  FileOutputStream fOut = null;
		  try {
			   f.createNewFile();
			   fOut = new FileOutputStream(f);
			   bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fOut);
			   fOut.flush();
			   fOut.close();
		  }catch (FileNotFoundException e) {
			  e.printStackTrace();
		  }catch (IOException e) {
			  e.printStackTrace();
		  }
	}
    

		//只适合小文件,应该分段读取,利用 ByteArrayOutputStream 
	public String encodeFile2Base64(String path) {
		
		FileInputStream inputFile = null;
		byte[] buffer =null;
		File  file = new File(path);
		
		try {
			inputFile = new FileInputStream(file);
			buffer = new byte[(int)file.length()];
			inputFile.read(buffer);
			inputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Base64.encodeToString(buffer, Base64.DEFAULT);
	}
	
	//只适合小文件,应该分段写入
	public void decodeBase642File(String targetPath,String b64String) {
		
		  byte[] buffer = Base64.decode(b64String, Base64.DEFAULT);
		  FileOutputStream out=null;
		try {
			out = new FileOutputStream(targetPath);
			out.write(buffer);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
    







}
