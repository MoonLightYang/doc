<center>
<h1>API通用信息格式</h1>
</center>

``` 
固定返回格式：
``` 	
	
<p style="line-height:20px;">
								{  </br>
&nbsp;&nbsp;&nbsp;&nbsp;			"info": "操作成功",  </br>
&nbsp;&nbsp;&nbsp;&nbsp;    		"code": 200,  </br>
&nbsp;&nbsp;&nbsp;&nbsp;			"data": {}  </br>
								} </br>
</p>
    	
``` 
返回字段含义：
``` 
|  字段    |   含义      |
| ------ | ------ |
|   info     |   提示信息      |
| code | 状态码 |
| data | 数据对象 |
    	

```
【code】状态码定义 
``` 

|   状态码     |   含义      |
| ------ | ------ |
| 200 | 操作成功 |
| 400 | 不合逻辑的操作。例：账号密码不对，参数形式不对，操作数据不存在等等所有提示结果 |
| 500 |  系统错误|

