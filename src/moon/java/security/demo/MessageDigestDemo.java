package moon.java.security.demo;

import moon.java.security.MessageDigestHelper;

class MessageDigestDemo {

	public static void main(String[] args) {
		//digestTestForString();
		
		fileDigest();
	}
	
	static void digestTestForString() {
		md5();		
		Logger.debug("---md5 ok---");
		
		sha1();
		Logger.debug("---sha1 ok---");
		
		sha224();
		Logger.debug("---sha224 ok---");
		
		sha256();
		Logger.debug("---sha256 ok---");
		
		sha384();
		Logger.debug("---sha384 ok---");
		
		sha512();
		Logger.debug("---sha512 ok---");
	}

	static void md5() {
		String digest = MessageDigestHelper.md5("C39685C0C3063755179EDF8891A16157B23431");
		MoonAssert.assertEqual(digest, "DC68FEDFA7895F36ED3B97E74010AD45");
	}

	static void sha1() {
		String s = "hello";
		String digest = MessageDigestHelper.sha1(s);
		MoonAssert.assertEqual(digest, "AAF4C61DDCC5E8A2DABEDE0F3B482CD9AEA9434D");
	}

	static void sha224() {
		String s = "hello";
		String digest = MessageDigestHelper.sha224(s);
		MoonAssert.assertEqual(digest, "EA09AE9CC6768C50FCEE903ED054556E5BFC8347907F12598AA24193");
	}

	static void sha256() {
		String s = "hello";
		String digest = MessageDigestHelper.sha256(s);
		MoonAssert.assertEqual(digest, "2CF24DBA5FB0A30E26E83B2AC5B9E29E1B161E5C1FA7425E73043362938B9824");
	}

	static void sha384() {
		String s = "hello";
		String digest = MessageDigestHelper.sha384(s);
		MoonAssert.assertEqual(digest, "59E1748777448C69DE6B800D7A33BBFB9FF1B463E44354C3553BCDB9C666FA90125A3C79F90397BDF5F6A13DE828684F");
	}

	static void sha512() {
		String s = "hello";
		String digest = MessageDigestHelper.sha512(s);
		MoonAssert.assertEqual(digest, "9B71D224BD62F3785D96D46AD3EA3D73319BFBC2890CAADAE2DFF72519673CA72323C3D99BA5C11D7C7ACC6E14B8C5DA0C4663475C2E5C3ADEF46F73BCDEC043");
	}

	static void fileDigest() {
		String filepath = "res/tmp.txt";
		Logger.debug("MD5:" + MessageDigestHelper.fileDigest(filepath, MessageDigestHelper.MessageDigestType.MD5));
	}
}
