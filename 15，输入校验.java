validate()和validateXxx():
validateXxx()方法专门用于校验xxx方法，
并且该方法是在xxx()方法处理逻辑前被调用。
而validate()方法对Action中的任何一个逻辑处理方法都起到娇艳2作用，
即不论请求Action中的哪一个逻辑处理方法，
validate()校验方法总是会对其进行校验。
如果被请求的Action中有对应的validateXxx()校验方法，
那么该校验方法将在validate()方法之前被执行。

输入校验流程：
1，客户端校验；
2，对请求的字符串参数进行类型转换，并设置为对应的Action属性值；
3，如果类型转换出现异常，将异常信息封装到fieldError中。这里无论是否产生转换异常，都将进入下一步；
4，调用Action的validateXxx()校验方法，其中xxx方法是Action中对应的处理逻辑方法；
5，调用Action的validate()校验方法；
6，完成上面的步骤后，框架开始检查在以上过程中是否产生了fieldError，如果产生了，则返回逻辑视图input；反之，则返回处理方法中的逻辑视图；
7，系统根据上一步骤返回的逻辑视图，结合struts.xml文件的配置内容，呈现相应的视图页面。


在国际化资源文件中：
增加invalid.fieldvalue.age=年龄输入错误
就可以将默认的更改！