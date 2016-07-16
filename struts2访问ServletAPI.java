Action对ServletAPI的访问有两种方式，分别是间接访问和直接访问。
对于间接访问，一般推荐使用，但是只能获得request对象，而得不到response对象；
直接访问分为Ioc访问方式和非Ioc访问方式，不推荐使用Ioc访问方式，因为该方式的实现比较麻烦，
而且与ServletAPI耦合大；推荐使用非Ioc方式，因为实现方式简单，代码量少而又能满足要求。

间接访问ServletAPI(推荐，但只能获得request对象)
需要extends ActionSupport
提供了名称为ActionContext的类，在Action中可以通过该类获得ServletAPI
提示：ActionContext是Action的上下文对象，Action运行期间所用到的数据都保存在ActionContext中，例如session会话和客户端提交的参数等信息。
创建ActionContext类对象的语法格式如下：
ActionContext ac = ActionContext.getContext();
方法：
Object get(String key);通过参数key来查找当前ActionContext中的值
Map<String, Object> getApplication();返回一个application级的Map对象
static ActionContext getContext();获取当前线程的ActionContext对象
Map<String, Object> getParameters();返回一个包含所有HttpServletRequest参数信息的Map对象
Map<String, Object> getSession();返回一个Map类型的HttpSession对象
void put(String key, Object value);向当前ActionContext对象中存入键值对信息
void setApplication(Map<String, Object> application);设置一个Map类型的application值
void setSession(Map<String, Object> session);设置一个Map类型的session值
实例：
继承ActionSupport后重写execute()方法：
public class UserAddAction extends ActionSupport {
	
	private String userName;
	private String password;
	@Override
	public String execte() throws Exception {
		ActionContext ac = ActionContext.getContext();
		ac.getApplication.put("uname", userName);
		ac.getApplication.put("upwd", password);
		return SUCCESS;
	}
}
[jsp中]
<s:property value="#application.uname">
<s:property value="#application.upwd">

直接访问ServletAPI
IoC方式：(不推荐)
ServletContextAware：实现该接口的Action可以直接访问Web应用的ServletContext实例;
ServletRequestAware：实现该接口的Action可以直接访问用户请求对象的HttpServletRequest的实例;
ServletResponseAware：实现该接口的Action可以直接访问服务器响应的HttpServletResponse的实例;
实例：
public class IoCAddAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {  
  
	private HttpServletRequest request;   
	private HttpServletResponse response;   
  
	public void setServletRequest(HttpServletRequest request) {  
		this.request = request;   
	}  
  
	public void setServletResponse(HttpServletResponse response) {  
		this.response = response;   
	}  
  
	public String execute() {   
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		return SUCCESS;   
	}  
	
	@Override
	public String execte() throws Exception {
		ServletContext application = ServletActionContext.getServletContext();
		application.setAttribute("uname", userName);
		application.setAttribute("upwd", password);
		return SUCCESS;
	}		
}  
非Ioc方式：(强烈推荐)
提供了一个名称为ServletActionContext的辅助类来获取ServletAPI.
在ServletActionContext类中有以下静态方法：
getPageContext(),getRequest(),getResponse()和getServletContext().
public class NoIoCAddAction extends ActionSupport {
	
	private String userName;
	private String password;
	
	@Override
	public String execte() throws Exception {
		ServletContext application = ServletActionContext.getServletContext();
		application.setAttribute("uname", userName);
		application.setAttribute("upwd", password);
		return SUCCESS;
	}
}
