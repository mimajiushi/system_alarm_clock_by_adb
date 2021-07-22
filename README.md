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

#### 特殊手机的适配

对于有【后台弹出界面】权限限制的手机，比如下面这个权限：
![image.png](https://www.wenjie.store/blog/img/image_1626943692408.png)

需要先执行`adb shell appops set wenjie.star.system_alarm_clock_by_adb 10021 allow`命令
执行完后【后台弹出界面】的权限虽然还是打x，但已经不影响了

说一下是怎么发现小米的这个问题的
- 安装好app后，没有权限，此时执行`adb shell appops get wenjie.star.system_alarm_clock_by_adb`
- 手动授予【后台弹出界面】权限后，再执行一次上一行的命令，对比如下
- ![image.png](https://www.wenjie.store/blog/img/image_1626943877824.png)
- 会发现授权后，有一个权限消失了，权限名就是`10021`，之后我尝试把这个属性设置成allow，没想到居然成功了


