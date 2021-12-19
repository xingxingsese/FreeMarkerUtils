package ${package};


public class ${className} {

<#--构建属性-->
<#list columns as column>
   <#if column??>
    /**
    * ${column.columnName}
    */
    private ${column.columnType} ${column.columnName};
   </#if>
</#list>

<#--构造get set方法-->
<#list columns as column>
    <#if column??>
    public Integer get${column.columnName?cap_first}() {
        return ${column.columnName};
    }

    public void set${column.columnName?cap_first}(${column.columnType} ${column.columnName}) {
        this.${column.columnName} = ${column.columnName};
    }
    </#if>
</#list>

}