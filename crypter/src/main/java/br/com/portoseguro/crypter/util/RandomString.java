package br.com.portoseguro.crypter.util;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class RandomString {
	static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom RANDOM = new SecureRandom();
	
	public static String randomString(int len){
	   StringBuilder sb = new StringBuilder(len);
	   for (int i = 0; i < len; i++) {
	      sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
	   }
	   return sb.toString();
	}
	
	public static void main(String[] args) {
		Map<String, String> teste = new HashMap<>();
		long a = 0;
		System.out.println("{\"tela\": \"abc.do\", \"data\": {\"sucursal\":\"34\", \"apolice\":\"123123123\", \"item\":\"19\"}}");
		while (true) {
			a+=1;
			String x = RandomString.randomString(8);
			if (teste.containsKey(x)) {
				System.out.println("repetiu em " + teste.size());
				throw new RuntimeException("Repetido!");
			}
			teste.put(x, "{\"tela\": \"abc.do\", \"data\": {\"sucursal\":\"34\", \"apolice\":\"123123123\", \"item\":\"19\"}}");
			if (a % 500000 == 0) {
				System.out.println(a + "-" + x + ": " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024*1024));
			}
		}
	}
}
