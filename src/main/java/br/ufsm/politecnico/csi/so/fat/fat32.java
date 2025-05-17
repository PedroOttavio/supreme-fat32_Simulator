package br.ufsm.politecnico.csi.so.fat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class fat32 implements FileSystem {
    private Disco disco = new Disco(); // inicia o disco
    private final int[] fat = new int[Disco.NUM_BLOCO];
    private static final int BLOCO_FAT = 0; // bloco 0 é o bloco da FAT
    // bloco 1 é o bloco do diretório
    private static final int BLOCO_DIRETORIO = 1;
    private static final int BLOCO_OCUPADO = -1;
    
    private List<EntradaDiretorio> diretorio = new ArrayList<>();

    private class EntradaDiretorio{
        private String fileName;
        private int tamanhoArquivo;
        private int blocoInicial;
    }

    public fat32(Disco disco) throws IOException {
        this.disco = disco;
        if (!disco.init()) { // se o disco não foi inicializado, inicializa
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
    private void inicializaDiretorio() throws IOException {
        // Inicializa o diretório
        // Preenche os campos do diretório){
        diretorio.clear(); //limpa a lista de diretorios
        gravaDiretorio();

    }
    private void inicializafat() throws IOException {
        fat[BLOCO_FAT] = BLOCO_OCUPADO;
        fat[BLOCO_DIRETORIO] = BLOCO_OCUPADO;
        gravaFat();
    }

    public void gravaDiretorio() throws IOException {
        // Inicializa o diretório
        // Preenche os campos do diretório){
        // gravaDiretorio();
        // O método gravaDiretorio deve:
//        gravaDiretorio deve:
//        Escrever todas as entradas da lista diretorio
//        Codificar as Strings com tamanho fixo
//        Preencher os campos corretamente
//        Escrever no disco via disco.write(BLOCO_DIRETORIO, ...)

        ByteBuffer buffer = ByteBuffer.allocate(Disco.TAM_BLOCO); // Cria um buffer para o bloco do diretório
        
        for (EntradaDiretorio entrada : diretorio) {
            //não sei direito se precisa do 
            String[] partes = entrada.fileName.split("\\."); // Converte o nome do arquivo para bytes
            String nomeArquivo = partes[0];
            String extensao = partes.length > 1 ? partes[1] : ""; // Verifica se tem extensão. Birlllll

            byte[] nome = new byte[8];
            byte[] ext = new byte[3];

            Arrays.fill(nome, (byte) ' '); // Preenche o array com 0
            Arrays.fill(ext, (byte) ' '); // Preenche o array com 0

            System.arraycopy(nomeArquivo.getBytes(), 0, nome, 0, Math.min(8, nomeArquivo.length()));
            System.arraycopy(extensao.getBytes(), 0, ext, 0, Math.min(3, extensao.length()));

            buffer.put(nome); // Adiciona o nome do arquivo ao buffer
            buffer.put(ext); // Adiciona a extensão do arquivo ao buffer
            buffer.putInt(entrada.tamanhoArquivo); // Adiciona o tamanho do arquivo ao buffer
            buffer.putInt(entrada.blocoInicial); // Adiciona o bloco inicial do arquivo ao buffer
         
        }
        //tava dando erro, talvez tenha sido porque eu coloquei ele dentro do for; verificar
        disco.write(BLOCO_DIRETORIO, buffer.array()); 

    }

    public void gravaFat() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(Disco.NUM_BLOCO);

        byte[] Bloco = new byte[Disco.NUM_BLOCO];
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
        //Deve retornar os nomes dos arquivos armazenados no diretório
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

    
}