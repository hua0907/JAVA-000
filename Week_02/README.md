一、查看GC日志的命令和解读
	命令：
		java -XX:+PrintGCDetails GCLogAnalysis
		java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
	命令解读：
		-XX:+PrintGCDetails : 输出GC信息
		-Xloggc:gc.demo.log: 输出GC信息到指定文件gc.demo.log
		-XX:+PrintGCDateStamps: 输出GC时间戳

二、GC日志信息和解读
	日志信息：
		[GC (Allocation Failure) [PSYoungGen: 32479K->5102K(37888K)] 32479K->11917K(123904K), 0.0040019 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
		[Full GC (Ergonomics) [PSYoungGen: 5110K->0K(136192K)] [ParOldGen: 99040K->98104K(200704K)] 104151K->98104K(336896K), [Metaspace: 2635K->2635K(1056768K)], 0.0280279 secs]
	参数解读：
		GC：发生youngGC 
		Full GC：发生fullGC
		PSYoungGen： young区大小
		ParOldGen: old区大小
		Metaspace: 元数据区大小
		Times：此次GC使用的时间
			user：用户态使用的时间
			sys：内核态使用的时间
			real：此次GC花费的总时间
	 
三、设置不同的xmx对GC的影响

                            生成对象情况(xms和xmx设置了相同的大小)				
                SerialGC	ParallelGC	    CMS	        G1
    128M	    堆内存溢出	堆内存溢出	    堆内存溢出	堆内存溢出
    256M	    4038	    3022	        4263	    堆内存溢出
    512M	    7438	    7228	        8899	    8326
    1024M	    9524	    9839	        10584	    10642
    2048M	    9423	    11706	        9794	    11036
    4096M	    8410	    10957	        8529	    9427
			
                            发生GC次数(xms和xmx设置了相同的大小)				
                SerialGC	                        ParallelGC	                        CMS	                                             G1
    128M	    9次youngGC，24次fullGC，然后堆溢出  	9次youngGC，17次fullGC，然后堆溢出	    young区是ParNew，old区CMS，然后堆溢出	        多次youngGC和Mix GC，然后堆溢出
    256M	    9次youngGC，19次fullGC	            12次youngGC，18次fullGC	            young区是ParNew，old区CMS	                多次youngGC和Mix GC
    512M	    14次youngGC	                        25次youngGC，5次fullGC	            young区是ParNew，old区CMS	                多次youngGC和Mix GC
    1024M	    9次youngGC	                        15次youngGC，1次fullGC	            young区是ParNew，old区CMS	                多次youngGC和Mix GC
    2048M	    4次youngGC	                        6次youngGC	                        只使用了ParNew，5次youngGC	                11次youngGC
    4096M	    2次youngGC	                        2次youngGC	                        只使用了ParNew，5次youngGC	                11次youngGC



                            吞吐量（60s内每s20个并发）				
            SerialGC	        ParallelGC	        CMS	                G1
    128M	3846.7--4152.4	    4729.4---4930.6	    5316--6065.2	    5107.9--5716.1
    256M	4146.1--4203.2	    5248.9--5952.9	    5518.3--5929.3	    5012.4--5676.8
    512M	4325.8--5628.6	    5622.1--6346.2	    4891.2--5405	    4722.5--5835.4
    1024M	5152--5676.3	    5188--6235.3	    5217.4--5706.1	    5205.6--5393.4
    2048M	4764.3--4793.7	    5286--5819.8	    4467.9--5482.4	    4876.6--5153.3
    4096M	3723.3---4086.7	    4192.4----4397.3	5127.6--5491.3	    3980.2--4107.5


四、jVM总结：

     1GC类型和特点
       SerialGC (串行GC)
	   young区：SerialNew old区：SerialOld
	   单线程的垃圾收集器，回收效率慢
	   
	   ParallelGC (并行GC)
	   young区：ParallelNew old区：ParallelOld
	   多线程的垃圾收集器，回收效率快,注重吞吐量
	   
	   CMS
	   young区：ParallelNew old区：CMS
	   并发的垃圾收集器，将GC的回收动作拆解成多步，可并发执行和不可并发执行，注重响应的时间
	   
	   G1
	   不分代,将堆内存分为小块（region），
	   并发的垃圾收集器，增量的进行GC，注重响应的时间可控制
    
    2、youngGC回收young区的垃圾，fullGC回收young区和old区的垃圾
    
    3、现象：第一次发生youngGC时，young区的内存使用大小和堆内存使用大小一致
       分析结论：验证了分代思想，刚产生的对象会被分配到young区
       
    4、现象：未设置xms的时候，每次youngGC，young区大小会变动，并且youngGC发生次数多于设置了xms的时候
	   分析结论: 未设置xms的时候，每次youngGC之后，都会初始化堆内存，由于初始堆内存较小，young区也会较小，会提前导致第一次youngGC时间提前
	
	5、现象：每次youngGC发生时 young区回收的内存大于整个堆回收的内存
	   分析结论：验证分代的现象，youngGC发生时 回收垃圾之外会将部分对象被提升到Old区
	
	6、现象：内存很小的时候，SerialGC性能会优于ParallelGC和CMS、G1
	   分析结论：此时SerialGC没有切换线程的时间成本	
	   
	7、现象：内存变大的时候，CMS、G1生成对象速度高于SerialGC、ParallelGC
	   分析结论：CMS，G1的发生GC是可以与业务线程并行，SerialGC、ParallelGC发生GC时会暂停业务线程
	
	8、将堆内存调大，能减少GC产生的次数，到达峰值之后继续增加堆内存，会导致低于峰值，随意堆内存并不是越大越好
	
	9、现象：相同的堆内存情况下，ParallelGC的吞吐量 高于CMS和G1，ParallelGC的生成对象的个数 低于CMS和G1
	   分析验证：ParallelGC在GC执行期间，无法执行业务线程，而CMS和G1在GC期间，可以执行业务线程，所以ParallelGC生成对象个数低于CMS和G1，
	            执行完GC之后，GC线程消失，cpu的可以用于全部服务于业务线程，而CMS和G1在GC之后，GC还是会和业务线程抢占CPU资源，所以整个单位时间ParallelGC吞吐量高于CMS和G1