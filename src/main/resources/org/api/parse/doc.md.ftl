<style>
	table th:nth-of-type(1) {
		align: center;
	    width: 3%;
	}
	table th:nth-of-type(2) {
	    width: 6%;
	}
	table th:nth-of-type(3) {
	    width: 4%;
	}
	table th:nth-of-type(4) {
	    width: 5%;
	}
	table th:nth-of-type(5) {
	    width: 8%;
	}
	table th:nth-of-type(6) {
	    width: 30%;
	}
	table tr:ntr-of-type(1) {
	    text-align: center;
	}
	.result th{
		background-color: #E4E4E4;
	}
</style>

``` 
接口描述 ：
``` 
**${api.describle!''}**
 
``` 
请求URI ：
``` 
**${api.url!''}**

``` 
请求方式 ：
``` 
**${api.way!''}**

```
请求参数 ：
```
<div>

<#list api.params as entity>
<#if entity_index == 0> <p><span>【入参对象】<#else> <span id="param_${entity.key}">【内部对象】 - ${entity.name}</#if>


|   序号      |   字段      |   类型       |  必填           |   含义      |  备注          |
| ------ | ------ | ------ | ------  | ------ |  ------  |
<#list entity.docs as fields>
| ${fields_index + 1} | <#if fields.isAnchors == 1><a href="#param_${fields.field}${fields_index + 1}" onclick="highLight('param_${fields.field}${fields_index + 1}')">${fields.field}</a><#else>${fields.field}</#if> | ${fields.type} | <font <#if fields.require == 'Y'>color="red"</#if>>${fields.require} </font>| ${fields.name} | ${fields.remark} |
</#list>
</#list>

</div>

```
返回信息 ：
```
<div class="result">
<#list api.results as entity>
<#if entity_index == 0> <p><span>【出参对象】<#else> <span id="rest_${entity.key}">【内部对象】 - ${entity.name}</#if>


|   序号      |   字段      |   类型       |   含义      |  备注          |
| ------ | ------ | ------ | ------ |  ------  |
<#list entity.docs as fields>
| ${fields_index + 1} | <#if fields.isAnchors == 1><a href="#rest_${fields.field}${fields_index + 1}" onclick="highLight('rest_${fields.field}${fields_index + 1}')">${fields.field}</a><#else>${fields.field}</#if> | ${fields.type} | ${fields.name} | ${fields.remark} |
</#list>
</#list>

</div>
<div style="height:200px;"></div>