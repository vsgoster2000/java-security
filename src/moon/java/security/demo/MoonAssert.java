package moon.java.security.demo;

public final class MoonAssert {
	public static final void assertTrue(boolean exp){
		AssertDebug.assertTrue(exp, "assertTrue failed");
	}

	public static final void assertTrue(boolean exp, String msg){		
		AssertDebug.assertTrue(exp, msg);	
	}	
	
	public static final void assertEqual(String lhs, String rhs){
		if (lhs != null) {
			assertTrue(lhs.equals(rhs), "hls:" + rhs + ", rhs:" + rhs);
			return ;
		}
		
		assertTrue(lhs == rhs, "hls:" + rhs + ", rhs:" + rhs);
	}

	private static class AssertDebug {
		
		final static void assertTrue(boolean exp, String msg){
			if (!exp){			
				failed(msg);
			}
		}		
			
		private static void failed(String msg){
			StackTraceElement[] temp=Thread.currentThread().getStackTrace();
			StackTraceElement a = temp[4];		

			String message = "Assertion failed:" + msg;
			message += ", filename(" + a.getFileName() + ")";
			message += " line(" + a.getLineNumber() + ")";
			
			throw new AssertException(message);
		}

	}
	
	private static class AssertException extends RuntimeException{
		private static final long serialVersionUID = 201411178L;

		public AssertException(String message) {
			super(message);
		}
	}
}
