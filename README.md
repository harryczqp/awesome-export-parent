# awesome-export

*一款可以开箱即用的，excel导出文件生成工具。*

## 快速上手
1. 添加pom引用

   ```xml
   <dependency>
         <groupId>com.suiyi</groupId>
         <artifactId>scm-download-center-export-autoconfigure</artifactId>
         <version>0.0.1-SNAPSHOT</version>
   </dependency>
   ```


## 项目介绍

### 架构介绍

TODO



### 文件结构

TODO

## 

### 核心技术

1. 导出组件会自动实现以下接口

   | 接口路径                               | 功能                                     | 备注                                        |
   | -------------------------------------- | ---------------------------------------- | ------------------------------------------- |
   | /export/autoBuildExportRequestTemplate | 自动构建请求模板                         |                                             |
   | /export/export2Base64                  | 自动生成导出文件-导出为Base64            |                                             |



## 

## 更新及规划

### 更新记录

0.0.1-SNAPSHOT 项目初始化 

- 实现了文件已base64的方式导出


### 更新规划

- 0.0.1-RC1
  - [x] 基于poi实现excel文件导出
  - [x] 添加一个启动类扫描工具并注入bean
  - [x] 导出文件支持转为base64
  - [x] 添加一个自动构建请求模板的方法
  - [x] 添加一些常用的公共方法
  - [x] 添加自动注入的方法
  - [x] 添加springboot地址组装工具类

