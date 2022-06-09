# awesome-export

*一款可以开箱即用的，excel导出文件生成工具。*

## 快速上手
1. 添加pom引用

   ```xml
        <dependency>
            <groupId>io.ggit.awesome</groupId>
            <artifactId>awesome-export-autoconfigure</artifactId>
            <version>0.0.1-RC1</version>
        </dependency>
   ```


## 项目介绍

### 架构介绍

#### 启动时序图

![启动时序图](https://raw.githubusercontent.com/harryczqp/awesome-export-parent/master/pic/启动时序图.png)

### 核心技术

1. 启动时BeanScanner自动扫描带有@CanBeExported的注解 根据注解value和请求路径存储bean信息
2. 启动后自动实现导出相关接口
3. 调用导出接口时，根据url参数找到bean信息执行查询方法
4. 得到数据集后，根据exportDetail参数 映射对应字段关系。若不想填写 也可以传入skipDetailChecked参数跳过
5. 生成的文件会保存在临时文件夹中 备用

### 文件结构

```sh
├─awesome-export-autoconfigure
│  └─src
│      └─main
│          ├─java
│          │  └─io.ggit.awesome.export
│          │     └─spring
│          │        └─config  //自动注册配置
│          └─resources
│              └─META-INF	  //spring.factories
├─awesome-export-core
│  └─src
│      ├─main
│      │  └─java
│      │  	 └─io.ggit.awesome.export
│      │         ├─model	//实体类
│      │         └─utils	//工具类
│      └─test
│          └─java
│              └─io.ggit.awesome.export
│                 └─utils	//工具类
└─pic
```

 

### 核心技术

1. 导出组件会自动实现以下接口

   | 接口路径                               | 功能                          | 备注 |
   | -------------------------------------- | ----------------------------- | ---- |
   | /export/autoBuildExportRequestTemplate | 自动构建请求参数模板          |      |
   | /export/export2Base64                  | 自动生成导出文件-导出为Base64 |      |



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
- 0.0.2-RC2
  - [ ] 参数检测友好化，计划使用反射的模式
  - [ ] 完善测试类
