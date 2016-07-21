在action中使用java.util.Date
在前台表单中：
<s:textfield name="date">2013-08-09 12:23:32</s:textfield>
从后台返回到前台展示时使用OGNL，
<s:date name="date" format="yyyy/MM/dd HH:mm:ss" />