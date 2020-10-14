import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Game {
	// Nome: Christian Schmidt

	// Para rodar outro arquivo coloque o arquivo dentro da pasta res e troque a
	// String path para exatamente o nome do arquivo.
	// No eclipse precisa dar refresh.

	static String path = "FuncoesResumo - SHA1.mp4";

	public static void main(String[] args) {

		ArrayList<byte[]> blocks = new ArrayList<byte[]>();
		// Cria um array de byte do arquivo escolhido
		byte[] bytesFile = Read.getBytes(path);
		// Tamanho de cada bloco
		int blocksize = 1024;
		// Numero de blocos que tem exatamento o tamanho escolhido
		int nblocks = (int) (bytesFile.length / blocksize);
		for (int i = 0; i < nblocks; i++) {
			byte[] block = new byte[blocksize];
			for (int j = 0; j < blocksize; j++) {
				block[j] = bytesFile[(i * blocksize) + j];
			}
			blocks.add(block);
		}
		// Se sobrar bytes, o ultimo bloco tera o tamanho dos bytes faltantes
		if (bytesFile.length % blocksize != 0) {
			int lastBlockSize = bytesFile.length % blocksize;
			byte[] lastBlock = new byte[lastBlockSize];
			for (int i = 0; i < lastBlockSize; i++) {
				lastBlock[i] = bytesFile[i + (bytesFile.length - lastBlockSize)];
			}
			blocks.add(lastBlock);
		}

		// Calcula o hash do ultimo bloco
		byte[] actualHash;
		actualHash = Hash.calculateHash(blocks.get(blocks.size() - 1), null);

		// Calcula o hash de cada bloco
		for (int i = blocks.size() - 2; i >= 0; i--) {
			actualHash = Hash.calculateHash(blocks.get(i), actualHash);
		}

		// Imprime o ultimo hash
		Hash.writeHash(actualHash);

	}

}
