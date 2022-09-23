package kr.or.funding.crypt;


import java.security.MessageDigest;

public class Sha256 {
	public static String testSHA256(String pwd) {
		try{

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}

			//출력
			return hexString.toString();
			
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
	}
}