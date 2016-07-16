【19】OGNL
1,直接访问值栈中的PropertyValue；
使用<s:property value="userName">
2,使用UserDTO私有变量getter和setter来接收前台参数时(自己未new，交由struts来new)，
必须使用user.userName作为前台的input的name,以及jsp显示时，
使用<s:property value="user.userName">来显示其值。
3,使用UserDTO，可以自己new，也可以让struts来帮我new,当由struts来new时，在上面2的条件基础上，
DomainModel必须提供一个参数为空的构造方法，用以struts来实例化一个DomainModel对象。否则会报错，
struts无法确定该new哪个构造方法。