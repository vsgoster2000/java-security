package moon.java.security;

/**
 * 字节转化器。负责将字节序列在ascii和bcd之间进行转换
 */
class ByteConverter {
	/**
	 * 将{@code bytes}指定的字节转换成已ASCII编码的字符串
	 * @param bytes 
	 * @return 返回结果字符串，如果{@code (bytes == null) || (bytes.length == 0)}返回null
	 */
	public static String bcdToAscii(final byte[] bytes){
		if ( (bytes == null) || (bytes.length == 0) ){
			return null;
		}
		
		return bcdToAscii(bytes, 0, bytes.length);
	}

	/**
	 * 将{@code bytes}字节数组从{@code start}到{@code end}转换成以ASCII编码的字符串。
	 * 包含{@code start}指定的位置，但是不包含{@code end}指定的位置，转换范围是[{@code start}, {@code end})
	 * @param bytes
	 * @param start 转换起始位置
	 * @param end 转换终止位置
	 * @return 返回结果字符串，如果{@code (bytes == null) || (bytes.length == 0)}或者
	 * {@code (start >= end) || (start >= bytes.length) || (end > bytes.length)}返回null
	 */
	public static String bcdToAscii(byte[] bytes, int start, int end){
		if ( (bytes == null) || (bytes.length == 0) ){
			return null;
		}
		if ( (start >= end) || (start >= bytes.length) || (end > bytes.length) ){
			return null;
		}
		char temp[] = new char[(end-start) * 2], val;

		for (int i = start; i < end; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[(i-start) * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[(i-start) * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}
	/**
	 * 将{@code ascii}指定的字符串转换成已BCD编码的数组
	 * @param ascii 源字符串
		 * @return 返回BCD编码数组，如果{@code (ascii == null) || (ascii.isEmpty())}返回null
	 */
	public static byte[] asciiToBcd(String ascii){
		if ( (ascii == null) || (ascii.isEmpty()) ){
			return null;
		}
		return asciiToBcd(ascii, 0, ascii.length());
	}

	/**
	 * 将{@code ascii}指定的字符串转换成已BCD编码的数组
	 * @param ascii 源字符串
		 * @return 返回BCD编码数组，如果{@code (ascii == null) || (ascii.isEmpty()) || (start >= ascii.length())}返回null
	 */
	public static byte[] asciiToBcd(String ascii, int start){
		if ( (ascii == null) || (ascii.isEmpty()) || (start >= ascii.length()) ){
			return null;
		}
		return asciiToBcd(ascii, start, ascii.length());
	}

	/**
	 * 将{@code ascii}指定的字符串的前{@code ascLen}字节转换成已BCD编码的数组
	 * @param ascii 源字符串
	 * @param ascLen 转换字节数
	 * @return 返回BCD编码数组，如果{@code (ascii == null) || (ascLen > ascii.length())}返回null
	 */
	public static byte[] asciiToBcd(String ascii, int start, int end){
		if ( (ascii == null) || (ascii.isEmpty()) ){
			return null;
		}
		if ( (start >= end) || (start >= ascii.length()) || (end > ascii.length()) ){
			return null;
		}
		final int bcdSize = (end - start) / 2;
		if (bcdSize == 0){
		    return null;	
		}
		byte[] bcd = new byte[bcdSize];
		int j = start;
		for (int i = 0; i < bcdSize; i++) {
			bcd[i] = asciiToBcd(ascii.charAt(j++));
			bcd[i] = (byte) (((j >= end) ? 0x00 : asciiToBcd(ascii.charAt(j++))) + (bcd[i] << 4));
		}
		return bcd;
	}	
	
	/**
	 * 将{@code asc}字符转换成bcd对应的值
	 */
	private static byte asciiToBcd(char asc){
		byte bcd;
		if ((asc >= '0') && (asc <= '9')){
			bcd = (byte) (asc - '0');
		}
		else if ((asc >= 'A') && (asc <= 'F')){
			bcd = (byte) (asc - 'A' + 10);
		}
		else if ((asc >= 'a') && (asc <= 'f')){
			bcd = (byte) (asc - 'a' + 10);
		}
		else{
			bcd = 0;
		}

		return bcd;
	}
}
