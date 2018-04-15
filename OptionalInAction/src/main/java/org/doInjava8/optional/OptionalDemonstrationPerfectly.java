package org.doInjava8.optional;

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
		
		List<Double> doubleList = ThreadLocalRandom.current().doubles(100_000).boxed()
						 .flatMap(flatMapper)
						 .collect(Collectors.toList());
		
		System.out.println(" # of doubleList "+doubleList.size());
		
		List<Double> doubleListInParallel = ThreadLocalRandom.current().doubles(100_000).parallel().boxed()
				 .flatMap(flatMapper)
				 .collect(Collectors.toList());
		
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
