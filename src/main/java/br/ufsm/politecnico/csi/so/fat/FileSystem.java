package br.ufsm.politecnico.csi.so.fat;

public interface FileSystem {            
        /**
         * Cria um novo arquivo.
         * @param fileName nome do arquivo para criar
         * @param data dados a serem salvos
         */
        public void create(String fileName, byte[] data);
        /**
         * create(String filename, byte[] data)
         * Verificar se o nome já existe
         *Alocar blocos livres
         *Encadear blocos na FAT
         *Gravar dados nos blocos
         *Criar EntradaDiretorio e salvar no diretório
         * Adiciona dados ao final do arquivo.
         */
		public void append(String fileName, byte[] data);
        /**
         * Lê arquivo.
         * @param fileName nome do arquivo
         * @param offset a partir de qual posição a leitura deve ser feita.
         * @param limit até aonde a leitura será feita, -1 para ler até o final do arquivo.
         * @return dados lidos
         *
         * Localizar entrada do arquivo
         *
         * Encontrar o último bloco (via FAT)
         *
         * Ver se cabe no último bloco
         *
         * Se não couber, alocar mais blocos
         *
         * Atualizar a FAT e o diretório
         *
         */
        byte[] read(String fileName, int offset, int limit);
        /**
         * Finalizar: navegar pela FAT a partir do bloco inicial, pular o offset e coletar dados até o limit
       //  * Remove o arquivo.
         * @param fileName
         */
        void remove(String fileName);
        /**
         * Localizar entrada
         * Liberar blocos na FAT (marcar como livres)
         * Remover entrada do diretório
         * Salvar FAT e diretório atualizados
         * Calcula o espaço disponível no sistema de arquivos.
         * @return bytes disponíveis
         */
        int freeSpace();
    }


