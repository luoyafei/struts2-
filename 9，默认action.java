<package name="main" extends="struts-default" namespace="/">
	<!-- 默认action，在url中随意敲击，可以跳转到指定地方。 -->
	<default-action-ref name="myIndex" />
	<action name="myIndex">
		<result>/tags.jsp</result>
	</action>

	<action name="index">
		<result>
			/exception.jsp
		</result>
	</action>
</package>

//<default-action-ref name="myIndex" />指定的action只能跳转到指定页面，不能使用action类来处理