��6��ʹ��action=mylogin!add����̬����������Ҫ
ʹ�õķ���
��7Լ���������á�ʹ��ͨ���*;(����ƥ���ȷ�ģ�)
<action name="*_*" class="com.learning.action.{1}Action" method="{2}">
//�����{1}��{2}�ֱ������*��1��ָ��һ��*��2��ָ�ڶ�����
<result>/{1}_{2}_success.jsp</result>
</action>
������Student��addʱ�������Զ�Ѱ��{1}Action��{2}��
��StudentAction�е�add������
ע�⣺name=��Student_add����name="*_*"����Student��add���ȼ��ߡ�
��8��ʹ��getter��setter������ȡ����ֵ
��9��ʹ��DomainModal���ղ�����
��UserAction extends ActionSupport�У�
private User user;
public void setUser(User user) {
	this.user = user;
}
public User getUser() {
	return user;
}
UserΪDomainModal����Bean�����������ݿ��е��ֶΣ�
ע�⣺����ע��ʱ������Ҫ����confirmingPassword�ֶΣ���������һ���м����UserDTO���������еĽ��չ������ֶΣ�
�ȴ������֮�󣬾Ϳ��Խ����ݿ��е��ֶθ�ֵ��User��
�ر�ע�⣺ʹ��DomainModal���ղ���������ʹ��UserDTO���ղ�������ǰ̨�ֶε�name�б��뽫��̨�����˽�г�Ա���磺
private UserDTO user;
����user����name�У�����<input type="text" name="user.userName">;�������ܱ�֤ǰ̨�������Ĳ�Ϊ�ա�

��10��default.properties��org.apache.struts2�£������Ǵ����struts.xml�еĳ���Ĭ��ֵ�ĵط�����struts.xml�еĳ���Ĭ��ֵ�ĵط�����struts
��ʹ��content�����Ḳ�ǵ����е�Ĭ��ֵ��
��11����jspҳ������ʹ�õ�<%@ taglib profix="s" uri="/struts-tags"%>,�ñ�ǩ����WETA-INF�µ�struts-tags.tld
�С�
��12����action extends ActionSupport�����еķ����У���������this.addFieldError("user", "userName error");
֮�󣬿����ڷ��ص���ͼ�У�ͨ��<s:fielderror></s:fielderror>ȡ��Ĭ�ϵĴ���
<s:property value="errors.user[0]"/>//ͨ�������ǩ��
//ȡ��value-stack�еģ�keyΪerrors��valueΪuser���ַ�������ĵ�һ��
<s:debug></s:debug>//������ʾ�����е���Ϣ