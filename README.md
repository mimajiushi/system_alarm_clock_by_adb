# new_clipper

#### 介绍

一个支持用adb设置 **系统** 闹钟的apk

#### 效果

（github图片会挂掉，所以点开链接看吧）  
https://www.wenjie.store/blog/img/%E9%97%B9%E9%92%9F%E8%AE%BE%E7%BD%AE_1626868736962.gif

#### 设置闹钟命令

对于有【后台弹出界面】权限限制的手机，需要先执行`adb shell appops set wenjie.star.system_alarm_clock_by_adb 10021 allow`命令
执行完后【后台弹出界面】的权限虽然还是打x，但已经不影响了

`adb shell am start-foreground-service -n wenjie.star.system_alarm_clock_by_adb/.AlarmClockService -a "set_alarm_clock" --es msg \"测试闹钟1\" --ei hour 20 --ei min 20`  
- 上面命令意思就是：晚上8点20分响起闹钟
- --es msg [闹钟备注]
- --ei hour[闹钟小时(24小时制)]
- --ei min [闹钟分钟]
