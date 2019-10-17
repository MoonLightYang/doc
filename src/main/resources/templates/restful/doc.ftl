<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <!-- 使用 rem 布局，使 H5 页面能适配不同设备屏幕尺寸 flexible-lite-1.0.js 用于计算 html 根元素的 font-size 大小 然后 css 或 less 中所有的尺寸值一定要用 rem 单位，而不是 px 或其它单位。 -->
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
    <script src="/website/flexible-lite/flexible-lite-1.0.js"></script>
    <script type="text/javascript"> flex(1000); </script>
    
    <!-- 务必确保在 less.js 之前加载你的样式表。 如果加载多个 .less 样式表文件，每个文件都会被单独编译。 因此，一个文件中所定义的任何变量、mixin 或命名空间都无法在其它文件中访问。 -->
    <link rel="stylesheet/less" type="text/css" href="/resource/css/doc.less?v=${v}">
    <link rel="stylesheet/less" type="text/css" href="/resource/css/md.less?v=${v}">
    <script type="text/javascript" src="/website/less/less-1.7.0.js"></script>

    <!-- 使代码根据语法显示样式（如：高亮等） -->
    <link rel="stylesheet" href="/resource/css/default.min.css">
    <script src="/resource/css/highlight.pack.js"></script>
    <script src="/resource/css/jquery-1.11.3.js"></script>
    <style>
    	.check_high_light{
    		background-color : #1E90FF;
    	}
    </style>
    <script type="text/javascript"> hljs.initHighlightingOnLoad(); 
    	function highLight(id){
    		$(".check_high_light").removeClass("check_high_light");
			$("#"+id).addClass("check_high_light");
		}
	</script>
</head>

<body>
	<div class="content">${content}</div>
</body>
</html>