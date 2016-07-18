【1】namespace决定了action的访问路径，默认为""，可以接收所有路径的action。
namespace可以写为/，或者/XXX，或者/XXX/YYY，对应的action访问路径为/index.action，/XXX/index.action,
或者/XXX/YYY/index.action。
namespace最好也用模块来进行命名。
【2】<package name="**">
作用于java中一致，避免重名的。
【3】<result name="success">
如果result中的name没有写，那么它的默认值就是success；
【4】如果是复制项目，将项目名字修改后，运行失败的话，必须将该项目右键properties，找Web Context root，将其修改为项目
名才能访问！
【5】路径问题
struts2中的路径问题是根据action的路径而不是jsp路径来确定，所有尽量不要使用相对路径。
虽然可以用redirect方式解决，但是redirect方式并非必要。
使用basePath
在head中加标签<base href="<%=basePath%>" />
这样在href中都会默认加上bashPath
【6】使用action=mylogin!add来动态方法调用想要
使用的方法
【7约定优于配置】使用通配符*;(首先匹配最精确的！)
<action name="*_*" class="com.learning.action.{1}Action" method="{2}">
//这里的{1}和{2}分别代表了*，1是指第一个*，2是指第二个。
<result>/{1}_{2}_success.jsp</result>
</action>
当传入Student和add时，将会自动寻找{1}Action和{2}，
即StudentAction中的add方法！
注意：name=“Student_add”比name="*_*"传入Student和add优先级高。
【8】使用getter和setter方法获取参数值
【9】使用DomainModal接收参数。
在UserAction extends ActionSupport中：
private User user;
public void setUser(User user) {
	this.user = user;
}
public User getUser() {
	return user;
}
User为DomainModal，即Bean，里面是数据库中的字段！
注意：例如注册时，还需要接收confirmingPassword字段，可以设置一个中间的类UserDTO，包含所有的接收过来的字段，
等处理完成之后，就可以将数据库中的字段赋值给User。
特别注意：使用DomainModal接收参数，或者使用UserDTO接收参数，在前台字段的name中必须将后台定义的私有成员例如：
private UserDTO user;
这里user加入name中，例如<input type="text" name="user.userName">;这样才能保证前台传过来的不为空。

【10】default.properties在org.apache.struts2下，其中是存放在struts.xml中的常量默认值的地方，在struts.xml中的常量默认值的地方，在struts
中使用content，将会覆盖掉其中的默认值。
【11】在jsp页面中所使用的<%@ taglib profix="s" uri="/struts-tags"%>,该标签是在WETA-INF下的struts-tags.tld
中。
【12】在action extends ActionSupport的类中的方法中，可以增加this.addFieldError("user", "userName error");
之后，可以在返回的视图中，通过<s:fielderror></s:fielderror>取出默认的错误，
<s:property value="errors.user[0]"/>//通过这个标签，
//取出value-stack中的，key为errors，value为user的字符串数组的第一个
<s:debug></s:debug>//可以显示出所有的信息
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

【19】OGNL
1,直接访问值栈中的PropertyValue；
使用<s:property value="userName">
2,使用UserDTO私有变量getter和setter来接收前台参数时(自己未new，交由struts来new)，
必须使用user.userName作为前台的input的name,以及jsp显示时，
使用<s:property value="user.userName">来显示其值。
3,使用UserDTO，可以自己new，也可以让struts来帮我new,当由struts来new时，在上面2的条件基础上，
DomainModel必须提供一个参数为空的构造方法，用以struts来实例化一个DomainModel对象。否则会报错，
struts无法确定该new哪个构造方法。

【20】
使用OGNL访问action中的一般方法，直接在<s:property value="m()">//这样可以访问action中的一般方法。
使用OGNL访问action中DomainModel成员变量中的方法，<s:property value="user.add()">//使用成员变量加.来访问。
使用OGNL访问类中的静态方法以及静态属性例子：<s:property value="@类名带包名@方法名或属性名" />。
注意：访问类中的静态方法时，<constant name="struts.ognl.allowStaticMethodAccess" value="true" />
必须将该项目加上，且设置为true。
使用OGNL访问普通类的构造方法：<s:property value="new com.learning.bean.User(8)" />//打印出来的是User的toString()方法
【21】使用OGNL访问List，Set，Map：
在action中：
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
访问List：<s:property value="users" />//将该User的List全部打印出来。
访问List中某个元素：<s:property value="users[1]">//users.get(0)
访问List中元素某个属性的集合：<s:property value="users.{age}" />
访问List中元素某个属性的集合中的特定值：
<s:property value="users.{age}[0]" />或者<s:property value="users[0].age" />//推荐使用后者

访问Set：<s:property value="dogs" />
访问Set中某个元素：<s:property value="dogs[1]" />
访问Map：<s:property value="dogMap" />
访问Map中某个元素：<s:property value="dogMap.dog101" />//访问Map中key为dog101的value
访问Map中所有的key：<s:property value="dogMap.keys" />
访问Map中所有的value：<s:property value="dogMap.values" />
访问容器的大小：<s:property value="dobMap.size()" />
【22】过滤
//this是指遍历循环users过程中的每个user当前对象
<s:property value="users.{?#this.age==1}" />
//拿到的是age==1的对象组成的数组
<s:property value="users.{?#this.age==1}[0]" />
//拿到的是age==1的对象组成的数组的第一个对象
<s:property value="users.{^#this.age>1}.{age}" />
//^#值的是拿到了对象的属性age>1的集合集的第一个
<s:property value="users.{$#this.age>1}.{age}" />
//$#值的是拿到了对象的属性age》1集合集的最后一个
<s:property value="users.{$#this.age>1}.{age}==null" />
//是否为空，返回为true/false
【23】OGNL表达式中括号[n]：
<s:property value="[0]" />//在值栈中第n个位置开始从上往下依次往出拿。

【作业】
1,读doc文档:struts-tags;
2,设计约定(编码规定)
