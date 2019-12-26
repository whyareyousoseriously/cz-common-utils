# cz-common-utils


## 介绍
一些通用的工具集合

## 文档
- [中文文档](http://wwww.xuewuzhijing9981.top/marathon-spring-boot-starter)

## 特性
- 1、日期转换工具
- 2、Http请求工具
- 3、IP工具
- 4、返回对象封装工具
- 5、lombok 1.18.8
- 6、guava 20.0
- 7、fastjson 1.2.47
- 8、xxl-excel 1.1.1
- 9、深拷贝
## 开始
maven
```
<dependency>
  <groupId>io.github.whyareyousoseriously</groupId>
  <artifactId>cz-common-utils</artifactId>
  <version>0.1.1-RELEASE</version>
</dependency>
```

### 1.日期转换工具
- 时间戳与时间互转
- 获取指定时间戳的零点时间戳
- 获取年份，月份，天，小时，分钟，秒，毫秒
- 获取下一月月初的时间戳
- 获取当月天数

### 9.深拷贝
- List集合的深拷贝，单个对象的深拷贝
```
@Test
    public void test(){
        List<String> l1 = Lists.newArrayList();
        l1.add("张山");
        List<String> l2 =  BeanCopierUtil.deepCopyList(l1);
        l2.add("李四");
        System.out.println(l1.toString());
        System.out.println(l2.toString());
    }

    @Test
    public void test2(){
        @Data
        class A{
            private String name;

        }
        A a = new A();
        a.setName("张山");
        A b = new A();
        BeanCopierUtil.deepCopyObject(a,b);
        b.setName("六六");
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(a.toString());
        System.out.println(b.toString());
    }
```