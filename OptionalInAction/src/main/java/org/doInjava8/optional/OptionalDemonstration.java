package org.doInjava8.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.doInjava8.optional.math.NewMath;

//The worst way of using streams because we can't have the 
//functionality of parallelism here ..s
//DON'T USE LIKE THIS
public class OptionalDemonstration {

	public static void main(String[] args) {
		
		//We can't process this in parallel
		List<Double> result = new ArrayList<>();
		
		ThreadLocalRandom.current().doubles(100_000).boxed()
		.forEach( value -> NewMath.inverse(value)
				.ifPresent(
						inv -> NewMath.sqrt(inv)
						.ifPresent(
								sqrt -> result.add(sqrt)
						)
				)
		
		);
		
		System.out.println("# of list "+result.size());
	}

}
