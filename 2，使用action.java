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