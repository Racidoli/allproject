import tornado.web
import tornado.ioloop
import suds
from  suds import client
from  phonetutils.phone import *
import json
from  phonetutils.cacheutils import  CacheService


class IndexHandler(tornado.web.RequestHandler):
       #get请求  self:当前对象
       def  get(self):
            print("接受到用户的get请求")
            #写一个消息
            self.render("login.html",failmsg=None);

class  UserHandler(tornado.web.RequestHandler):
        def post(self):
            print("接收到用户的user_*请求")
            #区分用户的请求的动作
            method=self.get_argument("action")
            print("method-->",method)
            if  method=="login":
                username=self.get_argument("username")
                userpwd=self.get_argument("userpwd")
                print(username,userpwd)

                url="http://127.0.0.1:8300/userdataservice/user?wsdl"
                service=suds.client.Client(url)
                msg=service.service.checkUserLogin(username,userpwd)
                print("msg--->",msg)

                #怎么来区分是浏览器还是手机的请求
                #得到请求的头
                headsInfo=self.request.headers

                #print("headsInfo-->",headsInfo)
                hinfo=headsInfo["User-Agent"]
                print("请求的头的信息为:",hinfo)
                val = checkPCorMobile(hinfo)
                print("val",val)

                jsonDatas=""
                if msg =='登录成功':
                    #菜单数据是不经常变化的，我们应从缓存中获取，不应该每次从数据库中响应
                    #减少对数据库服务器的负载。       方法1
                    '''url = "http://127.0.0.1:8300/userdataservice/user?wsdl"
                    service = suds.client.Client(url)
                    data = service.service.queryGridMenuData()
                    print("data-->", data)
                    print(type(data))
                    jsonDatas = json.loads(data)
                    print("jsonDatas-->", jsonDatas)'''

                    #方法2    从缓存中获取策略  内部缓存
                    cacheService=CacheService();
                    jsonDatas = cacheService.getMenuData("tmenudata")
                    if val=="PC":
                        self.render("index.html",contendata=jsonDatas)
                    else:
                        #json数据格式
                        self.finish({"msg":"success","contentdata":jsonDatas})
                else:
                    if  val =="PC":
                        self.render("login.html",failmsg=msg)
                    else:
                        self.finish({"msg":"fail"})
            elif method=="register":
                self.render("register.html")

class  AntvHandler(tornado.web.RequestHandler):
        def get(self):
            print("antv业务报表功能")
            method=self.get_argument("datas")
            print("method-->",method)
            if method=="classestostucount":
                url = "http://127.0.0.1:8300/userdataservice/user?wsdl"
                service = suds.client.Client(url)
                data = service.service.queryClassToStuCount()
                print("data-->",data)
                print(type(data))
                jsonDatas = json.loads(data)
                print("jsonDatas-->",jsonDatas)
                self.finish({"jsonDatas":jsonDatas})
            # elif  method=="querynamecount":
            #     url = "http://127.0.0.1:8300/userdataservice/user?wsdl"
            #     service = suds.client.Client(url)
            #     data = service.service.queryNameCount()
            #     print("data-->",data)
            #     print(type(data))
            #     jsonDatas = json.loads(data)
            #     print("namecountDatas-->",jsonDatas)
            #     self.finish({"namecountDatas":jsonDatas})



#设置配置项
settings={
    "template_path":"templates",
    "static_path":"static",
}


#创建一个应用对象
#包含路由，请求的动作名对应着业务类
app = tornado.web.Application([(r'/',IndexHandler),
                               (r'/user',UserHandler),
                               (r'/antv',AntvHandler),
                               ],**settings)

if  __name__ =="__main__":
       #绑定一个监听端口，内网穿透保持一致
       app.listen(8200)
       #启动web程序，开始监听端口的连接
       tornado.ioloop.IOLoop.current().start()


