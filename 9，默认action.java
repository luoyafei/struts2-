<package name="main" extends="struts-default" namespace="/">
	<!-- Ĭ��action����url�������û���������ת��ָ���ط��� -->
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

//<default-action-ref name="myIndex" />ָ����actionֻ����ת��ָ��ҳ�棬����ʹ��action��������