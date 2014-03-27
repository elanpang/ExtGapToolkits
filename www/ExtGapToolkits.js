/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

var exec = require('cordova/exec');
//var platform = require('cordova/platform');

/**
 * Provides access to notifications on the device.
 */

module.exports = {

   // dirty inject

	droidNaviTo:function(lat,lon) {
	    exec(null,null, "ExtGapToolkits", "droidNaviTo", [lat,lon]);
	},

	//haveInstalledBaiduMap:function(success) {
	//    exec(function(b) {
	//			success(b);
	//			},null, "ExtGapToolkits", "haveInstalledBaiduMap", null);
	//},

	haveInstalledBaiduMap:function(success) {
	    exec(success,null, "ExtGapToolkits", "haveInstalledBaiduMap", []);
	},

	naviWithBaiduMap:function(origin,destination) {
	    exec(null,null, "ExtGapToolkits", "naviWithBaiduMap", [origin,destination]);
	},
	
	clipImage:function(src,dest,x,y,w,h) {
	    exec(null,null, "ExtGapToolkits", "clipImage", [src,dest,x,y,w,h]);
	},
	
	resizeImage:function(src,dest,sample,quality) {
	    exec(null, null, "ExtGapToolkits", "resizeImage", [src,dest,sample,quality]);
	},

	encodeFile2Base64:function(success,targetPath) {
	    exec(success,null, "ExtGapToolkits", "encodeFile2Base64", [targetPath]);
	},

	decodeBase642File:function(targetPath,b64String) {
	    exec(null,null, "ExtGapToolkits", "decodeBase642File", [targetPath,b64String]);
	}

	//
 
};
