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

#import "CDVExtGapToolkits.h"
#import <Cordova/NSDictionary+Extensions.h>
#import <Cordova/NSArray+Comparisons.h>

@implementation CDVExtGapToolkits


//
- (UIImage*)loadImageFrom:(NSString*)path
{
    UIImage *imgFromPath=[[UIImage alloc]initWithContentsOfFile:path];
    //UIImageView* imageView=[[UIImageView alloc]initWithImage:imgFromUrl3];
    return imgFromPath;
}

- (void)saveImage:(UIImage*)image to:(NSString*)path
{
	//保存图片 2种获取路径都可以
	//NSArray*paths=NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	//NSString*documentsDirectory=[paths objectAtIndex:0];  
	//NSString*aPath=[documentsDirectory stringByAppendingPathComponent:[NSString stringWithFormat:@"%@.jpg",@"test"]]; 
	//NSString *aPath=[NSString stringWithFormat:@"%@/Documents/%@.jpg",NSHomeDirectory(),@"test"];
	//NSData *imgData = UIImageJPEGRepresentation(imgFromUrl,0);    
	//[imgData writeToFile:path atomically:YES];   

	NSData *imgData;
        if (UIImagePNGRepresentation(image) == nil) {
            imgData = UIImageJPEGRepresentation(image, 1);
        } else {
            imgData = UIImagePNGRepresentation(image);
        }
	[imgData writeToFile:path atomically:YES];
}



//String src,String dest,int x,int y,int w,int h
- (void)clipImage:(CDVInvokedUrlCommand*)command
{
    int x=[command argumentAtIndex:2];
    int y=[command argumentAtIndex:3];
    int w=[command argumentAtIndex:4];
    int h=[command argumentAtIndex:5];
    UIImage *image=[self loadImageFrom:[command argumentAtIndex:0]];
    CGImageRef subImageRef = CGImageCreateWithImageInRect(image.CGImage, CGRectMake(x,y,w,h));  
    CGRect smallBounds = CGRectMake(0, 0, CGImageGetWidth(subImageRef), CGImageGetHeight(subImageRef));  
    
    UIGraphicsBeginImageContext(smallBounds.size);  
    CGContextRef context = UIGraphicsGetCurrentContext();  
    CGContextDrawImage(context, smallBounds, subImageRef);  
    UIImage* smallImage = [UIImage imageWithCGImage:subImageRef];  
    UIGraphicsEndImageContext();  
    [self saveImage:smallImage to:[command argumentAtIndex:1]];
}

//String src,String dest,int sample,int quality
- (void)resizeImage:(CDVInvokedUrlCommand*)command
{
	int sample=[command argumentAtIndex:2];
	UIImage *image=[self loadImageFrom:[command argumentAtIndex:0]]; 
	UIGraphicsBeginImageContext(CGSizeMake(image.size.width / sample, image.size.height / sample);
	[image drawInRect:CGRectMake(0, 0, image.size.width * scaleSize, image.size.height * scaleSize)];
	UIImage *scaledImage = UIGraphicsGetImageFromCurrentImageContext();
	UIGraphicsEndImageContext();
	[self saveImage:scaledImage to:[command argumentAtIndex:1]];

        //CGSize itemSize=CGSizeMake(30, 30);  
	//UIGraphicsBeginImageContext(itemSize);  
	//CGRect imageRect=CGRectMake(0, 0, itemSize.width, itemSize.height);  
	//[img drawInRect:imageRect];  
	//i=UIGraphicsGetImageFromCurrentImageContext();  
	//UIGraphicsEndImageContext();  
	//return i;  
}

//String path
- (void)encodeFile2Base64:(CDVInvokedUrlCommand*)command
{
    NSData *imgData=nil;
    UIImage *image=[self loadImageFrom:[command argumentAtIndex:0]]; 
    if (UIImagePNGRepresentation(image) == nil) {
	imgData = UIImageJPEGRepresentation(image, 1);
    } else {    
	imgData = UIImagePNGRepresentation(image);
    }
    NSData * data = [imgData base64EncodedDataWithOptions:NSDataBase64Encoding64CharacterLineLength];
    NSString* base64Str=[NSString stringWithUTF8String:[data bytes]];
    CDVPluginResult* pluginResult = nil;
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:base64Str];
    //return [NSString stringWithUTF8String:[data bytes]];
}

String targetPath,String b64String
- (void)decodeBase642File:(CDVInvokedUrlCommand*)command
{
    NSData* decodedData = [[NSData alloc] initWithBase64EncodedString:[command argumentAtIndex:1] options:0]; 
    //NSLog(@"String is %@",[NSString stringWithUTF8String:[dataFromString bytes]]); // prints "String is Some sample data" 
    [decodedData writeToFile:[command argumentAtIndex:0] atomically:YES];
}


@end

