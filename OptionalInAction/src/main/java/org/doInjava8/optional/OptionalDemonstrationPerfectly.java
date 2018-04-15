package org.doInjava8.optional;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.doInjava8.optional.math.NewMath;

public class OptionalDemonstrationPerfectly {

	public static void main(String[] args) {
		
		Function<Double,Stream<Double>> flatMapper = 
				value -> NewMath.inverse(value)
				.flatMap(inv -> NewMath.sqrt(inv))
				.map(sqrt -> Stream.of(sqrt))
				.orElseGet(() -> Stream.empty());
		
		Instant time = Instant.now(); 
		
		List<Double> doubleList = ThreadLocalRandom.current().doubles(100_000).boxed()
						 .flatMap(flatMapper)
						 .collect(Collectors.toList());
		
		Instant time2 = Instant.now();
		
		System.out.println("Time taken in stream processing for 100_000 elements is"+(time2.compareTo(time)));
		
		System.out.println(" # of doubleList "+doubleList.size());
		
		Instant time3 = Instant.now();
		
		List<Double> doubleListInParallel = ThreadLocalRandom.current().doubles(100_000).parallel().boxed()
				 .flatMap(flatMapper)
				 .collect(Collectors.toList());
		
		Instant time4 = Instant.now();
		
		System.out.println("Time taken in parallel stream processing for 100_000 elements is"+(time4.compareTo(time3)));
		
		System.out.println(" # of doubleListInParallel "+doubleListInParallel.size());
		
		List<Double> doubleListInParallelWithNegative = ThreadLocalRandom.current()
				.doubles(100_000).parallel().map( value -> value*20 - 10).boxed()
				 .flatMap(flatMapper)
				 .collect(Collectors.toList());
		
		System.out.println(" # of doubleListInParallelWithNegative "+doubleListInParallelWithNegative.size());
		
		/**
		 * # of doubleList 100000
 		   # of doubleListInParallel 100000
           # of doubleListInParallelWithNegative 50069
		 */
	}

}
