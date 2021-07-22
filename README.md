# new_clipper

#### 介绍

一个支持用adb设置 **系统** 闹钟的apk

#### 效果

（github图片会挂掉，所以点开链接看吧）  
https://www.wenjie.store/blog/img/%E9%97%B9%E9%92%9F%E8%AE%BE%E7%BD%AE_1626868736962.gif

#### 设置闹钟命令

`adb shell am start-foreground-service -n wenjie.star.system_alarm_clock_by_adb/.AlarmClockService -a "set_alarm_clock" --es msg \"测试闹钟1\" --ei hour 20 --ei min 20`  
- 上面命令意思就是：晚上8点20分响起闹钟
- --es msg [闹钟备注]
- --ei hour[闹钟小时(24小时制)]
- --ei min [闹钟分钟]

 **启动service可能会有一定延迟**

#### 特殊手机的适配

##### 对于有【后台弹出界面】权限限制的手机
比如下面这个权限：
![image.png](https://www.wenjie.store/blog/img/image_1626943692408.png)

需要提前授权一些奇怪的数字，下面会一步步说什么是奇怪的数字：
- 首先执行：`adb shell appops get wenjie.star.system_alarm_clock_by_adb`
- ![image.png](https://www.wenjie.store/blog/img/image_1626947480514.png)
- 之后对其中`ignore`的地方授权
- 逐一执行命令（对应数字）👇
- ![image.png](https://www.wenjie.store/blog/img/image_1626947675623.png)
- 之后用get命令重新查看，可以看到变成allow了👇
- ![image.png](https://www.wenjie.store/blog/img/image_1626947728674.png)
- 之后再执行设置闹钟的命令就能正常执行了

如果你不确定到底是什么数字，那么在安装完应用后，写一个for脚本遍历数字去set就行了


###### 对于没有【后台弹出界面】权限的手机

这种手机通过service调用startActivity是会失败的（没有报错），但是对应权限的权限不同，比如我手上的华为P30 Pro就是

安装apk后
执行`adb shell appops set wenjie.star.system_alarm_clock_by_adb SYSTEM_ALERT_WINDOW allow`


