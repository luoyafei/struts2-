【1】namespace决定了action的访问路径，默认为""，可以接收所有路径的action。
namespace可以写为/，或者/XXX，或者/XXX/YYY，对应的action访问路径为/index.action，/XXX/index.action,
或者/XXX/YYY/index.action。
namespace最好也用模块来进行命名。
【2】<package name="**">
作用于java中一致，避免重名的。
【3】<result name="success">
如果result中的name没有写，那么它的默认值就是success；
