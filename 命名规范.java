1：数据库名：项目名；
2：表的命名：_Model名：
3：字段：保持和属性名一致(不要起名与数据库命名冲突)
3：用层来划分包：com.XXX.XXX.action model(bean) service dto(vo) dao
4：Action xxxxAction
5：*-*.jsp

service层中：接口：I开头；实现：接口+Impl


    板块命名首字母大写，采用驼峰标识
    表名与Model同名，加前缀t_
    action的类名为模块名 + 后缀Action
    每个模块原则上只配一个Action
    action在配置文件中的名字为模块名首字母小写
    增删改查的方法分别为add/update/delete/query
    采用*-*配置
    包名为模块名小写
    Action放在统一的包中
