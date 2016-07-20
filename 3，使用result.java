【13】
<result type="dispatcher/redirect/chain/redirectAction..">
dispatcher/redirect:服务器的转发和跳转，只是视图jsp或html。(dispatcher为默认typt)
chain/redirectAction：action上的转发和跳转；
使用action的跳转在标签内最前面不要加"/":
<result>
【14】
<result type="chain">
<param name="actionName">mylogin</param>//此为想要执行的action的name值
<param name="method">add</param>//此为想要执行的action的方法值
<param name="namespace">/secure</param>//此为想要执行的action的package的namespace
</result>
【15】
<global-result>
		<result name="exception">/exception.jsp</result>
</global-result>
在该package下，所有的action可以公用global-result里面的result。
若要在另外一个package中也使用该global-result，可以在这个另一个package中extends="含有global-result的package的name";
这样就也可以使用了。
【16】动态结果集
在UserAction中有一私有成员变量r，进行getter和setter。
在struts.xml中；可以使用${r},来在配置文件这里，获取到值栈(value-stack)中的值。
<action name="user" class="com.learning.action.UserAction">
	<result>${r}</result>
</action>
【17】带参数的结果集;向结果传参数。[redirect才需要这样传参数]
<result type="redirect">
	/user_success.jsp?t=${type}
</result>
【18】一个request共享一个值栈(value-stack),重新从客户端发起的request得使用【17】里面的方法
才可以把参数发送过去！
<s:property value="t" />//这里是从值栈{valuel-stack}中取值，这样无法获取，因为t的值是参数，
不在value-stack中存着。
<s:property value="#parameters.t" />//这里是从actioncontext中取值，t在parameters中，可以使用OGNL
获取到该参数t的值。