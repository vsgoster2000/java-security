package moon.java.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestHelper {
	public static enum MessageDigestType{
    	MD5("MD5"),
    	SHA1("SHA1"),
    	SHA224("SHA-224"),
    	SHA256("SHA-256"),
    	SHA384("SHA-384"),
    	SHA512("SHA-512");
    	
    	private String algorithm;
    	MessageDigestType(String algorithm){
    		this.algorithm = algorithm;
    	}
    	
    	public String algorithm() {
    		return algorithm;
    	}
    	
    	@Override
    	public String toString() {
    		return algorithm;
    	}
    }
	
	public static String md5(String input) {
		return digest(input, MessageDigestType.MD5);	 
	}
	
	public static String sha1(String input) {
		return digest(input, MessageDigestType.SHA1);	 
	}
	
	public static String sha224(String input) {
		return digest(input, MessageDigestType.SHA224);	 
	}
	
	public static String sha256(String input) {
		return digest(input, MessageDigestType.SHA256);	   
	}
	
	public static String sha384(String input) {
		return digest(input, MessageDigestType.SHA384);	   
	}
	
	public static String sha512(String input) {	   
	    return digest(input, MessageDigestType.SHA512);
	}
	
	private static String digest(String input, MessageDigestType type) {
		MessageDigestHelper helper = new MessageDigestHelper(type);
		helper.update(input);
		return toHex(helper.digest());
	}
	
	public static String fileDigest(String filepath, MessageDigestType type) {
		if (filepath == null) {
			return null;
		}
		return digest(new File(filepath), type);
	}
	public static String digest(File file, MessageDigestType type) {
		if (file == null) {
			return null;
		}
		MessageDigestHelper helper = new MessageDigestHelper(type);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = in.read(buffer, 0, 4096)) != -1) {  
				helper.update(buffer, 0, len);  
            }  
			return toHex(helper.digest());
		} catch (IOException e) {
			e.printStackTrace();			
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private final MessageDigestType mType;
	private MessageDigest mMessageDigest;	
	
	public MessageDigestHelper(MessageDigestType type){
		mType = type;
		try {
			mMessageDigest = MessageDigest.getInstance(mType.algorithm());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将数组中的所有字节加入摘要计算中
	 * @param input 字节数组，不能为null
	 */
    public void update(byte[] input) {
    	if (input == null) {
    		return ;
    	}
    	mMessageDigest.update(input, 0, input.length);
    }
    
    public void update(byte[] input, int offset, int len) {
    	mMessageDigest.update(input, offset, len);
    }
    
    /**
     * 采用默认的字符集将字符串编码成一个字节数组，然后将该数组加入到摘要计算中
     * @param input 一个字符串对象，不能为null
     */
    public void update(String input) {
    	if (input == null) {
    		return ;
    	}
    	mMessageDigest.update(input.getBytes());
    }
    
    /**
     * 采用指定的字符集将字符串编码成一个字节数组，然后将该数组加入到摘要计算中
     * @param input 一个字符串对象，不能为null
     * @param charset 一个字符串对象，表示一个字符集，不能为null
     */
    public void update(String input, String charset) {
    	if (input == null) {
    		return ;
    	}
    	try {
			mMessageDigest.update(input.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
    
    public byte[] digest() {
    	return mMessageDigest.digest();
    }
    
    public void reset() {
    	mMessageDigest.reset();
    }
    
    private static String toHex(byte[] bytes) {
	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}
}
