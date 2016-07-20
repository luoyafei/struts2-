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
【23】OGNL表达式中括号[]：
<s:property value="[0]" />//在值栈中第n个位置开始从上往下依次往出拿。