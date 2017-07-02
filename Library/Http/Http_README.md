# Http 网络请求框架

### 使用方法介绍
*1、使用方法
@HttpRequest(Tag = 20170702100)
public void sendRequest(){
    HttpRequest mRequest = new HttpRequest(Object...header,String body);
    RequestQueue().add(Tag,mRequest)
}

//必须实现
onHttpSuccess(int ResponseId,Response resp,String data,T t)

//一下三个可选择实现
onHttpFailure(int ResponseId,Response resp,Exception e)

onHttpCacheSuccess(int ResponseId,Response resp,String data,T t)

onHttpCacheFailure(int ResponseId,Response resp,Exception e)

2、采用请求队列

3、采用线程池

###设计
httpManager.request(Object obj)
           .requestId(int id)
           .requestMethod()
           .requestHeader(Object...header)
           .requestBody(Bean bean)
           .start()