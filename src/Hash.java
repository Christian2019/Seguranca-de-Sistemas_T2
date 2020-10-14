import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Hash {
	// Calcula o hash baseado no bloco atual e o ultimo hash
public static byte[] calculateHash(byte[] block, byte[] lastHash){

	//Para usar o SHA-256
	MessageDigest digest = null;
	try {
		digest = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
	
		e.printStackTrace();
	}
	
	byte[] hash=null;
	//Se for o ultimo bloco nao tem lastHash
	if (lastHash==null) {
	hash = digest.digest(block);
	}
	//Cria um novo Hash juntando o bloco atual e o ultimo hash
	else {
		byte[] novoHash= new byte[block.length+lastHash.length];
		for (int i=0;i<block.length;i++) {
			novoHash[i]=block[i];
		}
		for (int i=0;i<lastHash.length;i++) {
			novoHash[i+block.length]=lastHash[i];
		}
		//Calcula o hash desse novo bloco
		hash= digest.digest(novoHash);
	}
	
	return hash;
	
}
//Imprime o Hash
public static void writeHash(byte[] hash){
	StringBuffer hexString = new StringBuffer();
	 for (int i = 0; i < hash.length; i++) {
           String hex = Integer.toHexString(0xff & hash[i]);
           if(hex.length() == 1) hexString.append('0');
           hexString.append(hex);
       }
      
	System.out.println( hexString.toString());
}


}
