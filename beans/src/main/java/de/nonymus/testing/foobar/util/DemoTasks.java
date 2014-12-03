package de.nonymus.testing.foobar.util;

import java.math.BigInteger;

public class DemoTasks {
	
	public static BigInteger fac(long number) {
		BigInteger result = BigInteger.ONE;
		for (long i=2; i<= number; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

}
