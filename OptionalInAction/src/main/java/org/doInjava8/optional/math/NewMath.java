package org.doInjava8.optional.math;

import java.util.Optional;

public class NewMath {
	
	private NewMath() {
		
	}
	
	public static Optional<Double> sqrt(Double d){
		return (Optional<Double>) (d > 0d ? Optional.of(Math.sqrt(d)) : Optional.empty());
	}
	
	public static Optional<Double> inverse(Double d){
		return (Optional<Double>) (d != 0d ? Optional.of(1d/d) : Optional.empty());
	}
}
