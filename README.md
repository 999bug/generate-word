# generate-word
使用说明
1、将resources下面 wordreport 放到指定位置并修改 application.yml 

report:

  tmplPath: D:\\wordreport      //模板所在文件夹

  autoFlowTmpl: nba1.ftl   //模板名称

2、修改图片的位置，IReportServiceImpl.getFlowImage()方法，文件在 resources 下面 nba 目录下

3、启动postman 调用接口
http://localhost:10086/drm/report/download，并且携带参数 teamIds

4、通过查看日志找到日志生成位置

本项目支持批量生成文档

详细说明见博客 https://blog.csdn.net/qq_40965479/article/details/125446622?spm=1001.2014.3001.5502