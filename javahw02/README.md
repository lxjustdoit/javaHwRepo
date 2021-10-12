关于GC总结
1.默认-xmx为物理内存的1/4，当物理内存小于1g则默认1/2，一般将-xmx，-xms配置为一致
2.堆内存越大则GC越不频繁，但单次gc回收对象越大暂停时间变长，堆内存越小则gc越频繁，堆内存太小会发生oom
3.-xloggc：xxx.log 将gc日志输出到文件
4.日志内容：GC(产生原因)[yong区：gc前->gc后（最大容量）]堆内存gc前->gc后
5.full gc: 也会进行yonggc
6.java8默认parallel gc
7.-xmx和-xms不一致，-xms需要满足程序运行但最小内存
不同gc策略：
1.serial gc: +UseSerialGC, 单线程gc效率较低
2.parallel gc: +UseParallelGC， young gc+full gc
3.cms gc：+UseConcMarkSweepG，parNew young区gc，young gc后进入cms gc初始化标记，cms gc过程中可能会发生多次young gc，cms gc 最终标记
发生gc暂停（初始化标记同样暂停），之后进行并发清除和重置
4.g1 gc: +UseG1GC, young gc, 初始化标记，并行region扫描，并发标记和再标记 ，并发清理（cms升级版gc），full gc无法回收old区g1 gc
则会退化为serial gc