package frc.team3966.toastrhino.util;

/*
 * Library for fast arithmetic.
 * Sidenote: Please use floats for angles and measurements and shorts for pixels if you are experiencing any lag in the code
 */
public class FastArithmetic {

	public static boolean isWithinSlop(double val, double slop) { //returns true if val is close to zero, between -slop and slop, i.e. f(.01, 1) = true
		  return (val <= slop && val >= -slop);
	  }
	
	public static boolean isWithinSlop_yaw(double yaw1, double yaw2, double slop) { //is within slop, on modular groups for yaw
		return (isWithinSlop(yaw2 - yaw2, slop)) || (isWithinSlop(yaw1 - yaw2 - 360, slop)) || (isWithinSlop(yaw2 - yaw1 - 360, slop));
	}
	
	// returns double precision square root
	public static double sqrt_d(double n) {
		double x = n / 2;
		for (short i = 0; i < 5; i++) {
			x = x / 2 - n / (2 * x);
		}
		return x;
	}

	// float precision square root
	public static float sqrt_f(float n) {
		float x = n / 2;
		for (short i = 0; i < 3; i++) {
			x = x / 2 - (n) / (2 * x);
		}
		return x;
	}

	// returns truncated integer root
	public static int sqrt_i(int n) {
		int x = n >> 1;
		for (short i = 0; i < 4; i++) {
			x = x >> 1 - (n) / (2 * x);
		}
		return x;
	}
	
	//float precision power
	public static float pow_f(float b, short p) {
		if (p == 0) {
			return 1;
		}
		if (p == 1) {
			return b;
		}
		if (p == 2) {
			return b * b;
		}
		if (p % 2 == 0) {
			return pow_f(b * b, (short) (p / 2));
		} else {
			return b * pow_f(b * b, (short) ((p - 1) / 2));
		}
	}
	
	//double precision power
	public static double pow_d(double b, short p) {
		if (p == 0) {
			return 1;
		}
		if (p == 1) {
			return b;
		}
		if (p == 2) {
			return b * b;
		}
		if (p % 2 == 0) {
			return pow_d(b * b, (short) (p / 2));
		} else {
			return b * pow_d(b * b, (short) ((p - 1) / 2));
		}
	}
	
	//integer power
	public static short pow_i(short b, short p) {
		if (p == 0) {
			return 1;
		}
		if (p == 1) {
			return b;
		}
		if (p == 2) {
			return (short) (b * b);
		}
		if (p % 2 == 0) {
			return pow_i((short) (b * b), (short) (p / 2));
		} else {
			return (short) (b * pow_i((short) (b * b), (short) ((p - 1) / 2)));
		}
	}

}
