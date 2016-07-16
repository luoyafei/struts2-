Action��ServletAPI�ķ��������ַ�ʽ���ֱ��Ǽ�ӷ��ʺ�ֱ�ӷ��ʡ�
���ڼ�ӷ��ʣ�һ���Ƽ�ʹ�ã�����ֻ�ܻ��request���󣬶��ò���response����
ֱ�ӷ��ʷ�ΪIoc���ʷ�ʽ�ͷ�Ioc���ʷ�ʽ�����Ƽ�ʹ��Ioc���ʷ�ʽ����Ϊ�÷�ʽ��ʵ�ֱȽ��鷳��
������ServletAPI��ϴ��Ƽ�ʹ�÷�Ioc��ʽ����Ϊʵ�ַ�ʽ�򵥣��������ٶ���������Ҫ��

��ӷ���ServletAPI(�Ƽ�����ֻ�ܻ��request����)
��Ҫextends ActionSupport
�ṩ������ΪActionContext���࣬��Action�п���ͨ��������ServletAPI
��ʾ��ActionContext��Action�������Ķ���Action�����ڼ����õ������ݶ�������ActionContext�У�����session�Ự�Ϳͻ����ύ�Ĳ�������Ϣ��
����ActionContext�������﷨��ʽ���£�
ActionContext ac = ActionContext.getContext();
������
Object get(String key);ͨ������key�����ҵ�ǰActionContext�е�ֵ
Map<String, Object> getApplication();����һ��application����Map����
static ActionContext getContext();��ȡ��ǰ�̵߳�ActionContext����
Map<String, Object> getParameters();����һ����������HttpServletRequest������Ϣ��Map����
Map<String, Object> getSession();����һ��Map���͵�HttpSession����
void put(String key, Object value);��ǰActionContext�����д����ֵ����Ϣ
void setApplication(Map<String, Object> application);����һ��Map���͵�applicationֵ
void setSession(Map<String, Object> session);����һ��Map���͵�sessionֵ
ʵ����
�̳�ActionSupport����дexecute()������
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
[jsp��]
<s:property value="#application.uname">
<s:property value="#application.upwd">

ֱ�ӷ���ServletAPI
IoC��ʽ��(���Ƽ�)
ServletContextAware��ʵ�ָýӿڵ�Action����ֱ�ӷ���WebӦ�õ�ServletContextʵ��;
ServletRequestAware��ʵ�ָýӿڵ�Action����ֱ�ӷ����û���������HttpServletRequest��ʵ��;
ServletResponseAware��ʵ�ָýӿڵ�Action����ֱ�ӷ��ʷ�������Ӧ��HttpServletResponse��ʵ��;
ʵ����
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
��Ioc��ʽ��(ǿ���Ƽ�)
�ṩ��һ������ΪServletActionContext�ĸ���������ȡServletAPI.
��ServletActionContext���������¾�̬������
getPageContext(),getRequest(),getResponse()��getServletContext().
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
