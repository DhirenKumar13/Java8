package org.doInjava8.parallel.streams;

import java.util.stream.Stream;

public class ParallelStreamDemoFirst {

	public static void main(String[] args) {
		
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");
		
		//Order is guaranteed
		Stream.iterate("*",s -> s + "*").limit(5).forEach(System.out::println);
		
		//Order is not guaranteed
		//Stream.iterate("#", s -> s + "#").parallel().limit(5).forEach(System.out::println);
		
		//Order is not guaranteed and prints the thread that processed the '#' 
		Stream.iterate("#", s -> s + "#").parallel().limit(15)
		.peek(s -> System.out.println(s +" processed in the thread "+Thread.currentThread().getName())).forEach(System.out::println);
	}

}
