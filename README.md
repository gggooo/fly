# fly
lol
使用方法：
1、clone代码到本地
2、使用myeclipse中import--exsit maven project，选择刚才下载到的代码路径
3、右击项目名，properties-buildPath中需要加入JEE5的包，项目的字符为UTF8格式
4、myeclipse会自动building项目，查看class文件是否正常生成
5、将sql文件导入mysql
6、将common.util.Constant.USRCT改成自己的代码，以便于区分数据库中哪些是业务数据，以便于后续升级。
7、修改resource-spring-applicationContext-common.xml文件中的数据库链接
8、加载到tomcat中运行
9、打开链接显示登陆页面，输入admin,2,登陆成功即可。

自定义
1、用户类、组织类、组织用户关系类 同步主数据系统的数据

