��13��
<result type="dispatcher/redirect/chain/redirectAction..">
dispatcher/redirect:��������ת������ת��ֻ����ͼjsp��html��(dispatcherΪĬ��typt)
chain/redirectAction��action�ϵ�ת������ת��
ʹ��action����ת�ڱ�ǩ����ǰ�治Ҫ��"/":
<result>
��14��
<result type="chain">
<param name="actionName">mylogin</param>//��Ϊ��Ҫִ�е�action��nameֵ
<param name="method">add</param>//��Ϊ��Ҫִ�е�action�ķ���ֵ
<param name="namespace">/secure</param>//��Ϊ��Ҫִ�е�action��package��namespace
</result>
��15��
<global-result>
		<result name="exception">/exception.jsp</result>
</global-result>
�ڸ�package�£����е�action���Թ���global-result�����result��
��Ҫ������һ��package��Ҳʹ�ø�global-result�������������һ��package��extends="����global-result��package��name";
������Ҳ����ʹ���ˡ�
��16����̬�����
��UserAction����һ˽�г�Ա����r������getter��setter��
��struts.xml�У�����ʹ��${r},���������ļ������ȡ��ֵջ(value-stack)�е�ֵ��
<action name="user" class="com.learning.action.UserAction">
	<result>${r}</result>
</action>
��17���������Ľ����;������������[redirect����Ҫ����������]
<result type="redirect">
	/user_success.jsp?t=${type}
</result>
��18��һ��request����һ��ֵջ(value-stack),���´ӿͻ��˷����request��ʹ�á�17������ķ���
�ſ��԰Ѳ������͹�ȥ��
<s:property value="t" />//�����Ǵ�ֵջ{valuel-stack}��ȡֵ�������޷���ȡ����Ϊt��ֵ�ǲ�����
����value-stack�д��š�
<s:property value="#parameters.t" />//�����Ǵ�actioncontext��ȡֵ��t��parameters�У�����ʹ��OGNL
��ȡ���ò���t��ֵ��