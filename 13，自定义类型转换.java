编写继承DefaultTypeConverter的类，或者实现TypeConverter接口的类，
通过重写
public Object convertValue(Object value, Class toType) {
	// TODO Auto-generated method stub
	if(toType == Point.class) {
		Point p = new Point();
		String[] strs = (String[])value;
		String[] xy = strs[0].split(",");
		p.x = Integer.parseInt(xy[0]);
		p.y = Integer.parseInt(xy[1]);
		return p;
	}
	if(toType == String.class)
		return value.toString();
	return super.convertValue(value, toType);
}
方法。
在该方法中处理完成之后，必须得注册之后才能使用。
三种注册方式：//一般都用全局的注册发方式。
1，局部：XXXAction-conversion.properties。内容：p(属性名)=com.struts.conversion.MyPointConverter
2，全局：根目录下创建：xword-conversion.properties。内容：com.xxx.xxx.Point(类名)=com.struts.conversion.MyPointConverter
//注意：这里的p的=号右边的就是实现TypeConverter接口或DefaultTypeConverter类的自定义的类

3，Annotation

还有一种写法，从StrutsTypeConverter继承：
重写convertFromString和convertToString方法。

如果遇到非常麻烦的转换：
用request.setAttribute()来传递;
或session等。