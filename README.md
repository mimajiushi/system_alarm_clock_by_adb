# new_clipper

#### 介绍

一个支持用adb设置 **系统** 闹钟的apk

#### 效果

![闹钟设置](https://www.wenjie.store/blog/img/%E9%97%B9%E9%92%9F%E8%AE%BE%E7%BD%AE_1626868736962.gif)

#### 设置闹钟命令

`adb shell am start-foreground-service -n wenjie.star.system_alarm_clock_by_adb/.AlarmClockService -a "set_alarm_clock" --es msg \"测试闹钟1\" --ei hour 20 --ei min 20`  
- 上面命令意思就是：晚上8点20分响起闹钟
- --es msg [闹钟备注]
- --ei hour[闹钟小时(24小时制)]
- --ei min [闹钟分钟]

