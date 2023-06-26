import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class Fenicios {
    public char[][] mapa;
    public int[] posicoes = new int[18];
    public boolean[] portosAlcancaveis = new boolean[9];

    public Fenicios(String casoTeste) {
        readFile(casoTeste);
        // portosAlcancaveis(); --tentativa falha = mencionar
    }

    public int[] getPosicoes() {
        for (int i : posicoes)
        System.out.print(i + " ");
        return posicoes;
    }

    // public char[][] getMapa() {
    //     return mapa;
    // }

    // public int linhaPorto(char porto) {
    //     int i, j;
    //     for (i = 0; i < this.mapa.length; i++) {
    //         for (j = 0; j < this.mapa[0].length; j++) {
    //             if (this.mapa[i][j] == porto) {
    //                 return i;
    //             }
    //         }
    //     }
    //     return -1;
    // }

    // public int colunaPorto(char porto) {
    //     int i, j = 0;
    //     for (i = 0; i < this.mapa.length; i++) {
    //         for (j = 0; j < this.mapa[0].length; j++) {
    //             if (this.mapa[i][j] == porto) {
    //                 return j;
    //             }
    //         }
    //     }
    //     return -1;
    // }

    // public int caminhoTotal() {
    //     int total = 0, porto = 0;
    //     int inicioli, inicioco, fimli, fimco, ultimoPasso=0, passos=0;
    //     int auxLiI=0, auxCoI=0, auxLiF=0, auxCoF=0, portoAuxI=-1, portoAuxF=-1;
    //     for (int i=0; i<18; i+=2) {
    //         porto++;
    //         inicioli = posicoes[i];
    //         inicioco = posicoes[i + 1];
    //         if (i == 16) {
    //             fimli = posicoes[0];
    //             fimco = posicoes[1];
    //         } else {
    //             fimli = posicoes[i + 2];
    //             fimco = posicoes[i + 3];
    //         }

    //         ultimoPasso = passos;
    //         passos = bfs(inicioli, inicioco, fimli, fimco);

    //         if (ultimoPasso>0 && passos<0) {
    //             auxLiI = inicioli;
    //             auxCoI = inicioco;
    //             portoAuxI = porto;
    //         } else if (ultimoPasso<0 && passos<0) {
    //             auxLiF = fimli;
    //             auxCoF = fimco;
    //             int auxPassos = bfs(auxLiI, auxCoI, auxLiF, auxCoF);
    //             total += auxPassos;
    //             portoAuxF = porto + 1;
    //             System.out.println("Passos de " + portoAuxI + " até " + portoAuxF + ": " + auxPassos);
    //         }

    //         if (porto == 9)
    //             System.out.println("Passos de " + porto + " até 1: " + passos);
    //         else
    //             System.out.println("Passos de " + porto + " até " + (porto+1) + ": " + passos);

    //         System.out.println("ultimo passo: " + ultimoPasso);

    //         if (passos > 0)
    //             total += passos;
    
    //     }
    //     return total;
    // }

    public int viajar() {
        int operacoes = 0;
        int combustivel = 0;
        // int ultimoPortoValido = 0;
        int valor;  
        int liUltimo = posicoes[0], coUltimo = posicoes[1];

        for (int i=2; i<posicoes.length - 1; i+=2) {
            int destinoLi = posicoes[i], destinoCo = posicoes[i+1];
            operacoes++;
            valor = bfs(liUltimo, coUltimo, destinoLi, destinoCo);
            if (valor > 0) {
                combustivel += valor;
                liUltimo = destinoLi;
                coUltimo = destinoCo; 
                System.out.println("Passos: " + valor);
            }
        }

        valor = bfs(liUltimo, coUltimo, posicoes[0], posicoes[1]);
        combustivel += valor;
        System.out.println("operacoes em viajar: " + operacoes);
        return combustivel;
    }

    // public void portosAlcancaveis() {
    //     int[] dco = { -1, 1, 0, 0 };
    //     int[] dli = { 0, 0, -1, 1 };
    //     int terras;
    //     for (int i=0; i<18; i+=2) {
    //         terras = 0;
    //         for (int k = 0; k < 4; k++) {
    //             int auxLi = posicoes[i] + dli[k];
    //             int auxCo = posicoes[i+1] + dco[k];
    
    //             if (auxCo >= 0 && (auxCo < this.mapa[0].length) && auxLi >= 0 && (auxLi < this.mapa.length)) {
    //                 if (this.mapa[auxLi][auxCo] == '*') {
    //                     terras++;
    //                 }
    //                 System.out.print(this.mapa[auxLi][auxCo]);
    //             }
    //         }
    //         if(terras == 4) 
    //             //0 2 4 6 8 10 12 14 16
    //             portosAlcancaveis[i/2] = false;
    //         else 
    //             portosAlcancaveis[i/2] = true;
    //         System.out.println();
    //     }

    //     for (int i=0; i<9; i++)
    //         System.out.println(portosAlcancaveis[i]);
    // }

    // public int caminhoMaisCurto(int portoInicio, int portoFim) {
    public int bfs(int inicioli, int inicioco, int fimli, int fimco) {
        // adjacentes = norte, sul, leste, oeste
        // norte = 0 , 1
        // sul = 0, -1
        // leste = 1, 0
        // oeste = -1,

        // li = y
        // co = x
        // this.mapa.length = y = 100
        // this.mapa.length[0] = x = 150

        // 150 = x = mapa[0].length = co
        // 100 = y = mapa.length = li
        // 100 listas de 150 posicoes

        // checa se tem caminho em volta antes = poupca processamento
        int[] dco = { -1, 1, 0, 0 }; // Deslocamento na vertical
        int[] dli = { 0, 0, -1, 1 }; // Deslocamento na horizontal
        int operacoes = 0;

        // int inicioli = posicoes[portoInicio*2];
        // int inicioco = posicoes[(portoInicio*2)+1];
        // int fimli = posicoes[portoFim*2];
        // int fimco = posicoes[(portoFim*2)+1];

        // if (portosAlcancaveis[portoInicio] == false || portosAlcancaveis[portoFim] == false)
        //     return -1; AQUI TEM QUE TA DESCOMENTADO -> MAS DE UM JEITO QUE CONSIDERE AQUELA LISTA QUE O TURANI FALOU

        int passos = 0;
        int adicionadosUltimoPasso = 1;
        int proximoPasso = 0;
        boolean[][] visitado = new boolean[this.mapa.length][this.mapa[0].length];

        Queue<int[]> fila = new LinkedList<>();
        // Queue<Integer> filaLi = new LinkedList<>();
        // Queue<Integer> filaCo = new LinkedList<>();
        fila.add(new int[] { inicioli, inicioco });
        // filaLi.add(inicioli);
        // filaCo.add(inicioco);

        visitado[inicioli][inicioco] = true;

        while (!fila.isEmpty()) {
        // while (!filaLi.isEmpty() && !filaCo.isEmpty()) {
            int[] posicao = fila.poll();
            int li = posicao[0];
            int co = posicao[1];
            // int li = filaLi.poll();
            // int co = filaCo.poll();

            if (li == fimli && co == fimco) {
                System.out.println("operacoes em bfs (caminho encontrado): " + operacoes);
                return passos;
            }

            for (int k = 0; k < 4; k++) {
                int novoLi = li + dli[k];
                int novoCo = co + dco[k];
                // Verifica se o novo vizinho está dentro dos limites da matriz
                if (novoCo >= 0 && (novoCo < this.mapa[0].length) && novoLi >= 0 && (novoLi < this.mapa.length)) {
                    operacoes++;
                    // Verifica se o vizinho ainda não foi visitado
                    if (!visitado[novoLi][novoCo] && this.mapa[novoLi][novoCo] != '*') {
                        fila.add(new int[] { novoLi, novoCo });
                        // filaLi.add(novoLi);
                        // filaCo.add(novoCo);
                        visitado[novoLi][novoCo] = true;
                        proximoPasso++;
                    }
                }
            }
            adicionadosUltimoPasso--;
            if (adicionadosUltimoPasso == 0) {
                if (proximoPasso == 0) // nao adicionou nenhum caminho novo = tem que parar, pra nao continuar no loop
                    break;
                adicionadosUltimoPasso = proximoPasso;
                proximoPasso = 0;
                passos++;
            }
        }

        // marcacao = trocar o seu valor no mapa por 0 = se for 0 nao retorna pra ele
        // NA0, se precisar voltar em um outro moemnto n tem como
        // trocar por outro valor entao
        // adicionar em uma lista = percorridos - como no ex da aula
        // a cada novos adjacentes \
        System.out.println("operacoes em bfs(caminho não encontrado): " + operacoes);
        return -1; // nao encontrou caminho
    }

    // public int[] posicoesPortos() {
    //     int[] aux = new int[18];//2x9
    //     for (int i = 0; i < this.mapa.length; i++) {
    //         for (int j = 0; j < this.mapa[0].length; j++) {
    //             switch (this.mapa[i][j]) {
    //                 case '1':
    //                     aux[1] = i;
    //                     aux[0] = j;
    //                     break;
    //                 case '2':
    //                     aux[3] = i;
    //                     aux[2] = j;
    //                     break;
    //                 case '3':
    //                     aux[5] = i;
    //                     aux[4] = j;
    //                     break;
    //                 case '4':
    //                     aux[7] = i;
    //                     aux[6] = j;
    //                     break;
    //                 case '5':
    //                     aux[9] = i;
    //                     aux[8] = j;
    //                     break;
    //                 case '6':
    //                     aux[11] = i;
    //                     aux[10] = j;
    //                     break;
    //                 case '7':
    //                     aux[13] = i;
    //                     aux[12] = j;
    //                     break;
    //                 case '8':
    //                     aux[15] = i;
    //                     aux[14] = j;
    //                     break;
    //                 case '9':
    //                     aux[17] = i;
    //                     aux[16] = j;
    //                     break;
    //             }
    //         }
    //     }

    //     for (int k = 0; k < aux.length; k++) {
    //         System.out.print(aux[k] + " ");
    //     }

    //     return aux;
    // }//metodo antigo = tem que chamar ele separado, mas da pra colocar dentro = percorre uma vez só a lista

    public void readFile(String nomeArq) {
        Path path1 = Paths.get(nomeArq);
        int operacoes = 0;
        int cont = 0;
        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))) {
            String line = null;
            int linha = 0;
            while ((line = reader.readLine()) != null) {
                if (cont == 0) {
                    String[] dados = line.split(" ");
                    this.mapa = new char[Integer.parseInt(dados[0])][Integer.parseInt(dados[1])];// valores da primeira
                                                                                                 // linha sao o tam do
                                                                                                 // mapa
                    operacoes++;                                            
                    cont = 1;
                } else {
                    for (int i = 0; i < this.mapa[0].length; i++) {// percorre a quantidade exata de caracteres na
                                                                   // primeira linha = colunas em todas
                        this.mapa[linha][i] = line.charAt(i);// pega os caracteres de cada linha = li -> linha; co -> i
                        switch (line.charAt(i)) {// se for o numero, ja adiciona na lista d posicoes
                            case '1'://porto 1
                                this.posicoes[0] = linha;//linha
                                this.posicoes[1] = i;//i = colunas
                                break;
                            case '2':
                                this.posicoes[2] = linha;
                                this.posicoes[3] = i;
                                break;
                            case '3':
                                this.posicoes[4] = linha;
                                this.posicoes[5] = i;
                                break;
                            case '4':
                                this.posicoes[6] = linha;
                                this.posicoes[7] = i;
                                break;
                            case '5':
                                this.posicoes[8] = linha;
                                this.posicoes[9] = i;
                                break;
                            case '6':
                                this.posicoes[10] = linha;
                                this.posicoes[11] = i;
                                break;
                                case '7':
                                this.posicoes[12] = linha;
                                this.posicoes[13] = i;
                                break;
                                case '8':
                                this.posicoes[14] = linha;
                                this.posicoes[15] = i;
                                break;
                            case '9':
                                this.posicoes[16] = linha;
                                this.posicoes[17] = i;
                                break;
                        }
                        operacoes++;
                    }
                    linha++;// pula pra proxima linha linha=y
                }
            }
            System.out.println("operacoes readFile: " + operacoes);
        } catch (IOException x) {
            // System.err.format("Erro de E/S: %s%n", x);
            System.out.println("erro de leitura!!");
        }
    }

    public void printMapa() {
        for (int i = 0; i < this.mapa.length; i++) {
            for (int j = 0; j < this.mapa[0].length; j++) {
                System.out.print(this.mapa[i][j]);
            }
            System.out.println();
        }
    }
}
