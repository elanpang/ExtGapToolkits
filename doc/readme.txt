

haveInstalledBaiduMap调用示例

//	navigator.ExtGapToolkits.naviWithBaiduMap("31.32312,120.62134","34.264642646862,108.95108518068");

console.log('-----------1');
navigator.ExtGapToolkits.haveInstalledBaiduMap(
 function(b){ 
	console.log('-----------2:'+b);
	if (b) {
		console.log('-----------3'+x1);
		navigator.ExtGapToolkits.naviWithBaiduMap("31.52312,120.82134","31.32312,120.62134");
	}
	else
	{
		alertEx('请安装百度地图');
	}
 }
);