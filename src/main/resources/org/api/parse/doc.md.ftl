<style>
	table th:nth-of-type(1) {
		align: center;
	    width: 5%;
	}
	table th:nth-of-type(2) {
	    width: 15%;
	}
	table th:nth-of-type(3) {
	    width: 8%;
	}
	table th:nth-of-type(4) {
	    width: 5%;
	}
	table th:nth-of-type(5) {
	    width: 15%;
	}
	table th:nth-of-type(6) {
	    width: 15%;
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
<#list api.params as plist>
<#if plist_index == 0><p><span>【入参】     <#else><span id="jump">【入参内部对象】</#if><#if plist.field !="" >   字段 --> ${plist.field}</#if><#if plist_index != 0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#if>类型 --> ${plist.type}</span><#if plist_index == 0></p></#if>

|   序号      |   字段      |   类型       |  必填           |   含义      |  长度(范围) |  备注          |
| ------ | ------ | ------ | ------  | ------ |  ------  | ------ |
<#list plist.params as param>
| ${param_index + 1} |  <#if param.isAnchors == 1><a href="#jump" onclick="highLight('jump')">${param.field}</a><#else>${param.field}</#if> | ${param.type} | ${param.require} | ${param.name} | ${param.range} | ${param.remark} |
</#list>
</#list>
</div>

```
返回信息 ：
```
<div class="result">


<#list api.results as rlist>
<#if rlist_index == 0><p><span>【出参】      <#else><span id="jump">【出参内部对象】<#if rlist.field?? && rlist.field != "">   字段 --> ${rlist.field}</#if></#if><#if rlist_index != 0>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#if>类型 --> ${rlist.type}</span><#if rlist_index == 0></p></#if>
<#if api.results?size == 1 && rlist.isBasic != 1>

|   序号      |   字段      |   含义       |   类型         |   备注         |
| ------ | ------ | ------ | ------  | ------  |
<#elseif api.results?size gt 1>

|   序号      |   字段      |   含义       |   类型         |   备注         |
| ------ | ------ | ------ | ------  | ------  |
</#if> 
<#list rlist.results as result>
| ${result_index + 1}|${result.field!''}|${result.name!''}|${result.type!''}|${result.remark!''}| 
</#list>
</#list>
</div>
<div style="height:200px;"></div>