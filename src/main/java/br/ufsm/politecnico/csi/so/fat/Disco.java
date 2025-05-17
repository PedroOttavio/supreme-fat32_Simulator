package br.ufsm.politecnico.csi.so.fat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Disco {

    public static final int TAM_BLOCO = 64 *1024;
    public static final int NUM_BLOCO = 1024;
    private RandomAccessFile raf;

    public Disco(){}

    public boolean init() throws IOException {

        File f = new File("disco.dat"); // cria um novo disco;
        boolean exists = f.exists();
        if(exists){

        	//ras? Verificar se está correto, testar com 'rwd';
            raf = new RandomAccessFile(f, "rws");
            raf.setLength(NUM_BLOCO * TAM_BLOCO);

        }
        return exists;
    }

    public byte[] read(int numBloco) throws IOException {
        if (numBloco == 0 || numBloco > NUM_BLOCO){
            throw new IllegalArgumentException("numBloco must be between 0 and "+NUM_BLOCO);
        }
            raf.seek(numBloco * TAM_BLOCO);
            byte[] read = new byte[TAM_BLOCO];
            raf.read(read);
            return read;

    }

    public void write(int numBloco, byte[] data) throws IOException {
        if (numBloco == 0 || numBloco > NUM_BLOCO){
            throw new IllegalArgumentException("numBloco must be between 0 and "+NUM_BLOCO);
        }
        if (data == null || data.length == 0 || data.length > TAM_BLOCO){
            throw new IllegalArgumentException("data length must be between 0 and "+TAM_BLOCO);
        }
        raf.seek(numBloco * TAM_BLOCO);
        raf.write(data);
    }
}