防止重复提交得再form表单中加<s:token/>标签，
<s:form action="tokenAction" method="post">
	<s:textfield name="username" value="" />
	<s:token></s:token>
<s:form> 
这里的<s:token /> 标签会在页面中产生：
<input type="hidden" name="struts.token.name" value="struts.token" />
<input type="hidden" name="struts.token" value="C6JBYSHOLG5GS9RHO48G6JQDVDIW8PSM" />
这才是防止重复提交的关键。每次刷新，产生的value字符串都不相同。
后台先产生value值放到前台，之后，前台提交表单再与后台保存的值相互比较。

<!-- 这是用来测试重复提交token的Action -->
<action name="tokenAction" class="com.struts.action.TokenAction">
	<result>/index.jsp</result>
	<interceptor-ref name="defaultStack"></interceptor-ref>
	<interceptor-ref name="token"></interceptor-ref>
	<result name="invalid.token">/error.jsp</result>
	//也可以使用<result name="invalid.token" type="redirect">/testToken.jsp</result>重定向页面
</action>

//注意，在使用token拦截器之前，先使用defaultStack拦截器。
//当重发提交之后，token会返回invalid.token字符串，这样就可以自定义返回页面
