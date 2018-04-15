package org.doInjava8.parallel.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamDemoSecond {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		List<String> lists = new CopyOnWriteArrayList<>();
		
		//Not so efficient to use
		Stream.iterate("*",s -> s + "*").parallel()
		.limit(1000).forEach(s -> list.add(s));
		
		System.out.println("# "+list.size());
		
		//efficient enough
		lists = Stream.iterate("#", s -> s + "#").parallel().limit(1000)
		.collect(Collectors.toList());
		
		System.out.println("# "+lists.size());
	}

}
