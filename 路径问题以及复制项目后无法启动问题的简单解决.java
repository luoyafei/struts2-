【4】如果是复制项目，将项目名字修改后，运行失败的话，必须将该项目右键properties，找Web Context root，将其修改为项目
名才能访问！
【5】路径问题
struts2中的路径问题是根据action的路径而不是jsp路径来确定，所有尽量不要使用相对路径。
虽然可以用redirect方式解决，但是redirect方式并非必要。
使用basePath
在head中加标签<base href="<%=basePath%>" />
这样在href中都会默认加上bashPath