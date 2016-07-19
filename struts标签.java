【24】struts标签：
<s:property value="'username'">//加单引号，使用的字符串而非OGNL表达式。
<s:property value="admin" default="管理员" />//如何没有admin变量，使用默认值。
<s:property value="'<hr />'" excape="true"/>、
//escape默认为true，指的是将value中的html标签不要解析，直接显示字符串。反之为解析为html内容。
【25】s:set标签；
<s:set var="adminName" value="username"/>//这里的username是ognl的值(Object类型)
set为设定adminName的值(范围scope默认为：request和ActionContext中)
使用以下获取：
从request中取值：<s:property value="#request.adminName" />
从ActionContext中取值：<s:property value="#adminName" />

【26】s:bean标签
<s:bean var="myuser" name="这里填写的是类名包括包名">//这里相当于是新建了一个类
//注意这里的var相当于new出来的对象的名。如果不使用var，那么该对象创建过程中只会在值栈中存在一会，
//创建完成之后又消失，要想长久存在，必须增加var，这里new出来的对象就进入了actionContext中了。

<s:param name="name" value="'value'"></s:param>
//这里的name于bean对象的setter方法对应，value中填写的是String值，而非ognl，注意加单引号区分
</s:bean>
取值：<s:property value="#myuser" />//这样就能获得整个类
特别注意：在让struts帮我们new对象时，该对象必须存在一个空的构造方法，否则就无法创建对象。

【27】%{}的用处：
使用<s:include value="%{#url}" />
//这里include标签的value值默认是字符串的，即输入什么就是什么而不是ognl
//如果想要它是ognl表现的值，必须得加上%{}，这样才能替换。
【28】$#%的区别
$用于i18n和struts配置文件
#取得ActionContext的值
%将原本的文本属性解析为ognl，对于本来就是ognl的属性不起作用。
参考<s:property value="这里直接放入的就是ognl">
<s:include value="这里直接放入就是文本属性">
【29】在ognl的parameters中获取参数，必须指名是第几个！
<s:property value="#parameters.age[0]" />
使用if：
<s:if test="#parameters.age[0] < 0"> wrong age !</s:if>
<s:elseif test="#parameters.age[0] < 20">too young!</s:elseif>
<s:else>yeah!</s:else>

【30】s:iterator遍历集合
<s:iterator value="{1,2,3}">//ognl的大括号将其中的值转换为集合形式
	<s:property/>//这样可以直接挨个遍历出来
</s:iterator>
自定义变量：
<s:iterator value="{'aaa','bbb','ccc'}" var="x">//for(String s:List<String> ss){}//与这个类似s就是x
	<s:property value="#x.toUpperCase()" />
</s:iterator>
使用status:
<s:iterator value="{'aaa','bbb','ccc'}" status="status">
	<s:property />
	遍历过的元素总数：<s:property value="#status.count" />
	遍历过的元素索引：<s:property value="#status.index"/>
	当前是偶数？：<s:property value="#status.even"/>
	当前是奇数？：<s:property value="#status.odd"/>
	是第一个元素吗：<s:property value="#status.first" />
	是最后一个元素吗：<s:property value="#status.last" />
</s:iterator>
定义map：
<s:iterator value="#{1:'a',2:'b',3:'c'}">//定义map得在{}前面加#
	<s:property value="key" />|<s:property value="value" />
</s:iterator>

<s:iterator value="#{1:'a',2:'b',3:'c'}" var="x">//定义map得在{}前面加#
	<s:property value="#x.key" />|<s:property value="#x.value" />
</s:iterator>
