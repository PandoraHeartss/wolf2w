# 狼行天下

## 项目介绍

### 项目目的

1，项目的总体安排；

2，项目的重点：

    1，本项目的重点不是去制作一个完整旅游点评项目，而是了解整个旅游点评项目各个流程和需求，对实际项目开发有一个较为深刻的理解；
    
    2，掌握项目中的一些重要的第三方工具/框架；比如bootstrap；uploadify;ueditor;等的使用；
    
    3，掌握提升自我价值的知识点；比如redis,mongodb,elasticsearch，mybatis-plus
    
    4，掌握如何从0到1开发项目。

3，项目的学习方法：

	1，深入学习和理解项目的需求，和一个产品的设计理念；
	
	2，这个项项目，重点不要纠结于细节的代码实现，要从更大的范围去理解一个项目/产品的开发过程；
	
	3，理解项目中的相关业务流程，学会自己去阅读第三方开发文档等（分享/第三方登录/短信发送）；
	
	4，从原理上掌握更高级的工具的使用，重点是要理解，什么时候需要用到这些东西，和使用这些东西的基本方式；

### 项目需求

### 项目演示

### 技术路线

我们在做架构的时候并没有讲到SSH，SSM。这些东西不叫架构.做叫做技术路线.在做架构的时候根本就没必要在意用得是什么语言,或者什么框架。

项目技术路线:

1，数据库：mysql + mongodb + elasticsearch +Redis （缓存）

2，持久化层：spring-data ; mybatis-plus

3，业务层：Springboot；

4，Web：SpringMVC；

5，前端：

       管理后台：jQuery+Bootstrap3
    
       前端展示：vue +jquery + css；



6：开发模式

       管理后台：以前传统模式(类似crm)
    
       前端展示：前后端分离方式

### 项目搭建

- 项目拆分

	- trip-website
	- trip-website-api
	- trip-mgrsite

- maven项目结构分析

- 项目组成结构

	- 父项目

		- wolf2w

		  1：对子项目进行版本管理

		  2：对值项目的依赖进行管理

	- 核心组件

		- trip-core

		  业务核心逻辑

		  

		  domain/repository/service/query/util等

	- 管理后端

		- trip-mgrsite

		  维护前端展示数据

		  

		  controller/模板页面

	- PC前端数据接口

		- trip-website-api

		  前端展示数据请求接口，返回纯json格式数据

	- PC前端

		- trip-website

		  发起请求，请求trip-website-api接口，获取数据并展示数据，此项目为纯静态页面

- 代码实现