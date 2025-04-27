package br.ufsm.politecnico.csi.so.fat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


public class fat32 implements FileSystem {
    private Disco disco = new Disco();
    private int[] fat = new int[Disco.NUM_BLOCO];
    private static final int BLOCO_FAT = 0;
    private static final int BLOCO_DIRETORIO = 1;
    private static final int BLOCO_OCUPADO = -1;

    public fat32(Disco disco) throws IOException {
        this.disco = disco;
        if (!disco.init()) {
            inicializafat();
            inicializaDiretorio();
        }else{
            leFat();
            leDiretorio();
        }
    }
    private void leDiretorio(){

    }
    private void leFat() throws IOException {

        byte[] bloco = disco.read(BLOCO_FAT);
    }
    private void inicializaDiretorio(){

    }
    private void inicializafat() throws IOException {
        fat[BLOCO_FAT] = BLOCO_OCUPADO;
        fat[BLOCO_DIRETORIO] = BLOCO_OCUPADO;
        gravaFat();
    }
    private void gravaFat() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(Disco.NUM_BLOCO);

        byte[] Bloco = new byte[Disco.NUM_BLOCO];;
        for(int i : fat){
            buffer.putInt(i);
        }
        disco.write(BLOCO_FAT, buffer.array());

    }

    @Override
    public void create(String filename, byte[] data) {

    }
    @Override
    public void append(String fileName, byte[] data) {

    }
    @Override
    public byte[] read(String fileName, int offset, int limit) {
        // 1- buscar o arquivo no diretorio pelo nome;
        // 2-
        /*  numBloco = diretorio.blocoInicial;
            byte[] arquivo = new byte[diretorio.tamanho];

            do{
            ler o bloco
            grava o bloco do arquivo
            numBloco =
            }while(nomeBloco : 0)
        */

        return new byte[0];
    }

    public List<String> listaArquivos(){
        return null;
    }

    @Override
    public void remove(String fileName) {

    }
    @Override
    public int freeSpace() {
        int livres = 0;
        for(int i : fat){
            if(i ==0){
                livres++;
            }
        }
        return livres + Disco.TAM_BLOCO;
    }

    private List<EntradaDiretorio> diretorio = new ArrayList<>();

    private class EntradaDiretorio{
        private String fileName;
        private int tamanhoArquivo;
        private int blocoInicial;
    }
}