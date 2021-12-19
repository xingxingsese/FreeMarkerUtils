<#--注释-->
欢迎您:${username}
<#-- if指令-->
<#if flag=1>
    <--if指令测试-->
    传入数据=1
    <#elseif flag=2>
    传入数据=2
    <#else>
    传入数据=其他
</#if>

<--list指令测试1-->
<#--list指令
    item?index )： 循环中当前项的索引(从0开始的数字)。
    item?has_next )： 辨别当前项是否是序列的最后一项的布尔值。
    <#break> 跳出当前循环
-->
<#list weeks as it>
    索引值:${it?index} == 当前值是:${it} ,是否还有下一个: <#if it?has_next>是<#else>否</#if>
   今天是: ${it}
    <#if it?index=4>
    ${it}是25号
    <#break>
    </#if>
</#list>

<#--模版包含 include-->
<#include "template02.ftl">

<#-- assign指令 : 在ftl模版中定义数据存到root节点下-->
<#assign name="zhangsan"/>
assign中的变量值为${name}

<#--内置函数 freemarker内置了很多函数,
    ?lower_case: 字符串的小写形式
    ?upper_case: 字符串的大写形式
    ?trim: 去掉字符串首尾的空格
    等等
    格式:
       ${变量?函数名}
-->
${name?upper_case}