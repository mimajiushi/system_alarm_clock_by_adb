# new_clipper

#### 介绍

一个支持用adb设置 **系统** 闹钟的apk

---

#### 效果

github图片会挂掉，所以点开链接看吧：

小米：https://www.wenjie.store/blog/img/%E8%AE%BE%E7%BD%AE%E9%97%B9%E9%92%9F_%E5%B0%8F%E7%B1%B3_1626953593249.gif<br/>
华为：https://www.wenjie.store/blog/img/%E9%97%B9%E9%92%9F%E8%AE%BE%E7%BD%AE_%E5%8D%8E%E4%B8%BA.gif

> 可以看到小米的屏幕会闪，而华为P30不会，这是系统决定的，我也控制不了

---

#### 特别提醒

**在运行设置闹钟、设置定时器命令之前，请先【手机适配】环节**

#### 设置闹钟命令

`adb shell am start-foreground-service -n wenjie.star.system_alarm_clock_by_adb/.AlarmClockService -a "set_alarm_clock" --es msg \"测试闹钟1\" --ei hour 20 --ei min 20`
- 上面命令意思就是：晚上8点20分响起闹钟
- --es msg [闹钟备注]
- --ei hour[闹钟小时(24小时制)]
- --ei min [闹钟分钟]

---

#### 设置定时器命令
`adb shell am start-foreground-service -n wenjie.star.system_alarm_clock_by_adb/.TimerService -a "set_timer" --es msg \"测试定时器1\" --ei afterSec 30`
- 上面命令意思就是：30秒后响起计时器（跟闹钟发出同样的信号）
- --es msg [计时器备注]
- --ei afterSec [计时器倒计时]

---

- 如果运行上述命令后出现错误：`Error: Not found; no service started.`<br/>
- 那么请执行一次`adb shell am start wenjie.star.system_alarm_clock_by_adb/.MainActivity`<br/>
- 原因是后台进程被杀了，后台进程能不能保活又得看厂商的配置，所以最终建议就是完成下面的手机适配后，每次执行设置闹钟命令之前，都执行激活

---

#### 手机适配

如果安装apk后直接执行上面的设置闹钟命令，那是必定无效的（可能没有报错），为了适配各种手机版本+权限，你需要看完下面的设置教程

对于不同版本的手机，各有各的适配方案，请有耐心的看完，当初我也废了不少时间实验

---

##### 对于有【后台弹出界面】权限限制的手机

比如下面这个权限的手机：<br/>
![image.png](https://www.wenjie.store/blog/img/image_1626952858672.png)

我们需要执行命令：`adb shell appops set wenjie.star.system_alarm_clock_by_adb [手机厂商权限key] allow`
你可能很疑惑[手机厂商权限key]是个啥，没关系，下面就会讲到

要找出[手机厂商权限key]，我们需要如下操作
- 安装完apk后，执行命令：`adb shell appops set wenjie.star.system_alarm_clock_by_adb SYSTEM_ALERT_WINDOW allow`，没有报错就是成功
- 再执行命令：`adb shell appops get wenjie.star.system_alarm_clock_by_adb`，下简称`get`命令
![image.png](https://www.wenjie.store/blog/img/image_1626950316508.png)
- 注意红框那条，等下就消失了
- 然后我们在手机手动开启【后台弹出界面】的权限：
![image.png](https://www.wenjie.store/blog/img/image_1626950759196.png)
- 再次执行`get`命令，会发现`10021`这个权限不见了：
![image.png](https://www.wenjie.store/blog/img/image_1626950811963.png)
- 到此为止，我们就成功发现[手机厂商权限key]就是`10021`

---

- 接着我们再把【后台弹出界面】的权限关掉：
![image.png](https://www.wenjie.store/blog/img/image_1626952858672.png)
- 执行命令（你也可以再用set命令验证一次）：`adb shell appops set wenjie.star.system_alarm_clock_by_adb 10021 allow`，下简称`set`命令
![image.png](https://www.wenjie.store/blog/img/image_1626952976052.png)
- 没有报错，那就是成功了，接着再执行设置闹钟的命令就能成功了

如果你不确定到底是什么数字，那么在安装完应用后，写一个for脚本遍历数字去set就行了

---

##### 对于没有【后台弹出界面】权限的手机

这种手机通过service调用startActivity是也是会失败的（没有报错），但是对应权限的权限不同，比如我手上的华为P30 Pro就是

但这种手机设置权限非常简单，仅仅需要在安装apk后执行如下命令即可：
`adb shell appops set wenjie.star.system_alarm_clock_by_adb SYSTEM_ALERT_WINDOW allow`

之后再执行闹钟的命令就能成功了

---




