��1��namespace������action�ķ���·����Ĭ��Ϊ""�����Խ�������·����action��
namespace����дΪ/������/XXX������/XXX/YYY����Ӧ��action����·��Ϊ/index.action��/XXX/index.action,
����/XXX/YYY/index.action��
namespace���Ҳ��ģ��������������
��2��<package name="**">
������java��һ�£����������ġ�
��3��<result name="success">
���result�е�nameû��д����ô����Ĭ��ֵ����success��
��4������Ǹ�����Ŀ������Ŀ�����޸ĺ�����ʧ�ܵĻ������뽫����Ŀ�Ҽ�properties����Web Context root�������޸�Ϊ��Ŀ
�����ܷ��ʣ�
��5��·������
struts2�е�·�������Ǹ���action��·��������jsp·����ȷ�������о�����Ҫʹ�����·����
��Ȼ������redirect��ʽ���������redirect��ʽ���Ǳ�Ҫ��
ʹ��basePath
��head�мӱ�ǩ<base href="<%=basePath%>" />
������href�ж���Ĭ�ϼ���bashPath
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

��19��OGNL
1,ֱ�ӷ���ֵջ�е�PropertyValue��
ʹ��<s:property value="userName">
2,ʹ��UserDTO˽�б���getter��setter������ǰ̨����ʱ(�Լ�δnew������struts��new)��
����ʹ��user.userName��Ϊǰ̨��input��name,�Լ�jsp��ʾʱ��
ʹ��<s:property value="user.userName">����ʾ��ֵ��
3,ʹ��UserDTO�������Լ�new��Ҳ������struts������new,����struts��newʱ��������2�����������ϣ�
DomainModel�����ṩһ������Ϊ�յĹ��췽��������struts��ʵ����һ��DomainModel���󡣷���ᱨ��
struts�޷�ȷ����new�ĸ����췽����

��20��
ʹ��OGNL����action�е�һ�㷽����ֱ����<s:property value="m()">//�������Է���action�е�һ�㷽����
ʹ��OGNL����action��DomainModel��Ա�����еķ�����<s:property value="user.add()">//ʹ�ó�Ա������.�����ʡ�
ʹ��OGNL�������еľ�̬�����Լ���̬�������ӣ�<s:property value="@����������@��������������" />��
ע�⣺�������еľ�̬����ʱ��<constant name="struts.ognl.allowStaticMethodAccess" value="true" />
���뽫����Ŀ���ϣ�������Ϊtrue��
ʹ��OGNL������ͨ��Ĺ��췽����<s:property value="new com.learning.bean.User(8)" />//��ӡ��������User��toString()����
��21��ʹ��OGNL����List��Set��Map��
��action�У�
	List<Cattle> cattles = new ArrayList<Cattle>();
	Set<Cat> cats = new HashSet<Cat>();
	Map<String, Dog> dogs = new HashMap<String, Dog>();
	
	public List<Cattle> getCattles() {
		return cattles;
	}

	public void setCattles(List<Cattle> cattles) {
		this.cattles = cattles;
	}

	public Set<Cat> getCats() {
		return cats;
	}

	public void setCats(Set<Cat> cats) {
		this.cats = cats;
	}

	public Map<String, Dog> getDogs() {
		return dogs;
	}

	public void setDogs(Map<String, Dog> dogs) {
		this.dogs = dogs;
	}
	
	
	public MyAction() {
		cattles.add(new Cattle("cattle1", 1));
		cattles.add(new Cattle("cattle2", 2));
		cattles.add(new Cattle("cattle3", 3));
		
		cats.add(new Cat("cat1", 1));
		cats.add(new Cat("cat2", 2));
		cats.add(new Cat("cat3", 3));
		
		dogs.put("dog1", new Dog("dog1", 1));
		dogs.put("dog2", new Dog("dog2", 2));
		dogs.put("dog3", new Dog("dog3", 3));
	}
����List��<s:property value="users" />//����User��Listȫ����ӡ������
����List��ĳ��Ԫ�أ�<s:property value="users[1]">//users.get(0)
����List��Ԫ��ĳ�����Եļ��ϣ�<s:property value="users.{age}" />
����List��Ԫ��ĳ�����Եļ����е��ض�ֵ��
<s:property value="users.{age}[0]" />����<s:property value="users[0].age" />//�Ƽ�ʹ�ú���

����Set��<s:property value="dogs" />
����Set��ĳ��Ԫ�أ�<s:property value="dogs[1]" />
����Map��<s:property value="dogMap" />
����Map��ĳ��Ԫ�أ�<s:property value="dogMap.dog101" />//����Map��keyΪdog101��value
����Map�����е�key��<s:property value="dogMap.keys" />
����Map�����е�value��<s:property value="dogMap.values" />
���������Ĵ�С��<s:property value="dobMap.size()" />
����ҵ��
1,��doc�ĵ�:struts-tags;
2,���Լ��(����涨)
