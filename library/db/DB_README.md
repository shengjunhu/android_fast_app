# OBObject Module

### 文档说明
* 1、采用GreenDao的新数据库 ObjectBox
* 2、注解映射数据库
* 3、有点数据读取速率较高

### 采用建议
```
    android app 缓存的数据主要是缓存设备数据、用户数据、服务器网络数据。
设备数据和用户数据使用SharedPreferences存储足够，操作方便，使用简单！
服务器网络数据，如果数据量少，可手写见表，不需要入第三方。如果是大量采用缓存，可采用objectbox

```