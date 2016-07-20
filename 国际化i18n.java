在src根目录下增加国际化资源文件：
resourceName_language_country.properties
resourceName代表自定义的资源文件名，
language表示地区语言代码，不能自定义，
country表示国家地区代码，也不能自定义。
例如：app_zh_CN.properties,app_en_US.properties
properties文件的内容以key-value的形式存在
使用中文的properties得用properties的视图打开中文得使用ascii值
使用方式：
在properties中：welcome=hello
ResourceBundle rsb = ResourceBundle.getBundle("app", Locale.CHINA);
String welcome = rsb.getString("welcome");
System.out.println(welcome);

在struts.xml中配置:<constant name="struts.custom.i18n.resources" value="app"/>
//这里配置的value值就是自定义的国际化资源文件名
在jsp页面中使用i18n:
访问方式：
<s:property value="getText('welcome')">

使用占位符：
在properties文件中，welcome=hello:{0}
在jsp中：
<s:text name="welcome">
	<s:param value="luo" />//这里的参数value值是什么，对应的占位符就是什么
</s:text>

使用动态语言切换：
在url中+?request_locale=en_US等。
注意这里加该参数的是传入action的链接，而非直接的url.jsp链接