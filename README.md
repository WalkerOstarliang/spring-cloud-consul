# spring-cloud-consul

1、 consul安装

（1）安装包下载地址:
    https://www.consul.io/downloads.html
    根据自己的系统下载相应的安装包
（2）unzip consul_1.2.2_linux_amd64.zip

 (3) sudo cp ./consul /usr/local/bin（为方便放在此路径, root用户操作）
 
（4）检查是否安装成功
    ➜  ~ consul version
      Consul v1.2.2
      
2、consul 服务器端启动

  nohup consul agent -server -bind=本机IP  -bootstrap-expect=1 -client=0.0.0.0  -data-dir=/data/consul/data/ -node=server1 >/dev/null 2>&1 &
  
  -server 表示是以服务端身份启动

  -bind 表示绑定到哪个ip（有些服务器会绑定多块网卡，可以通过bind参数强制指定绑定的ip）

  -client 指定客户端访问的ip(consul有丰富的api接口，这里的客户端指浏览器或调用方)，0.0.0.0表示不限客户端ip

  -bootstrap-expect=3 表示server集群最低节点数为3，低于这个值将工作不正常(注：类似zookeeper一样，通常集群数为奇数，方便选举，consul采用的是raft算法)

  -data-dir 表示指定数据的存放目录（该目录必须存在）

  -node 表示节点在web ui中显示的名称
  
  在浏览器里，访问下，类似 http://IP地址:8500/，正常的话，应该会看到一行文字：Consul Agent。
  
  根据实际情况启动服务端的个数 bootstrap-expect是配置个数的参数
  
 3、consul 客户端启动
 
   nohup consul agent -client=0.0.0.0  -data-dir=/Users/apple/consul/data -node=client1 -ui >/dev/null 2>&1 &
  
  4、组建集群
    在任意节点运行 consul members 
    查看节点信息
    Node     Address             Status  Type    Build  Protocol  DC   Segment
    server1  xx.xx.xx.200:8301     alive   server  1.2.2  2         dc1  <all>
    
    分别在各个节点加入集群
    consul join xx.xx.xx.200
    成功后输出
    Successfully joined cluster by contacting 1 nodes.
    再查看
   appledeMacBook-Air:springcloud apple$ consul members
    Node     Address             Status  Type    Build  Protocol  DC   Segment
    server1  xx.23.163.79:8301  alive   server  1.2.2  2         dc1  <all>
    client1  xx.168.1.172:8301  alive   client  1.2.2  2         dc1  <default>
    
    tips: 如果反过来，要将1个节点从集群中撤掉，可以在该节点上运行consul leave 即可。
    
   5、web ui
   client启动的时候有个参数 -ui，这代表将启动consul自带的web管理界面，访问 http://xx.xx.xx.xx:8500/ui
    ![image](https://github.com/WalkerOstarliang/spring-cloud-consul/blob/master/picture/consul%E9%A6%96%E9%A1%B5.jpg)
  
  6、spring cloud 集成 consul
  
  本文使用idea创建项目File ->> New ->> Project ->>Spring Initialir ->> {type:maven project ; packaging: jar} ->> {Web:Web; Cloud    Discovery : Consul Discovery； Ops ： Actuator} ->> Finish
    spring-cloud支持consul的服务发现与注册，Actuator 支持健康检查，springboot具体配置：
    pom.xml
    ![image](https://github.com/WalkerOstarliang/spring-cloud-consul/blob/master/picture/pom.xml.jpg)
    
   application.propeties
    ![image](https://github.com/WalkerOstarliang/spring-cloud-consul/blob/master/picture/application.properties.jpg)
    
   controller:
    ![image](https://github.com/WalkerOstarliang/spring-cloud-consul/blob/master/picture/controller.jpg)
    
   启动spring boot便可
   登录consul查看 spring-cloud-consul服务注册完成
    ![image](https://github.com/WalkerOstarliang/spring-cloud-consul/blob/master/picture/spring-cloud-consul-register.jpg)
    
   7、集群部署
    可以重新复制一份代码 修改pom.xml中的spring.cloud.consul.discovery.instance-id=agent2,同时端口修改一下
    重新启动spring boot
    登录consul点开spring-cloud-consul 可以看见有多个node提供一个服务:
    ![image](https://github.com/WalkerOstarliang/spring-cloud-consul/blob/master/picture/spring-cloud-consul-nodes.jpg)
    
    
    

    
  
   
  
  
  
