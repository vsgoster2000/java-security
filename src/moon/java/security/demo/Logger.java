package moon.java.security.demo;

public class Logger {
	public static void debug(String msg) {
		System.out.println(msg);
	}
	
	public static void debug(String format, Object...args) {
		System.out.println(String.format(format, args));
	}
	
	public static void info(String msg) {
		System.out.println(msg);
	}
	
	public static void info(String format, Object...args) {
		System.out.println(String.format(format, args));
	}
	
	public static void warn(String msg) {
		System.out.println(msg);
	}
	
	public static void warn(String format, Object...args) {
		System.out.println(String.format(format, args));
	}
	
	public static void error(String msg) {
		System.out.println(msg);
	}
	
	public static void error(String format, Object...args) {
		System.out.println(String.format(format, args));
	}

}
