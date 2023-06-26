public class App {
    public static void main(String[] args) throws Exception {
        long inicio = System.currentTimeMillis();
        Fenicios mapa01 = new Fenicios("/Users/kristen/Downloads/3semestre/alestII/t2/Fenicios/src/Casos/case5.map");
        // mapa01.printMapa();
        // int[] posicoes = mapa01.getPosicoes();
        
        // mapa01.posicoesPortos();

        //BFS - breadth first search, queue everything = every move, is a step
        //transform grid into graph - nodes (need some kind of flag = visited)
        //adjacent list/matrix = see what are the next steps to the BFS
        // https://www.youtube.com/watch?v=KiCBXu4P-2Y&t=46s&ab_channel=WilliamFiset
        // http://www.maxgcoding.co m/astar/
        // https://www.lavivienpost.com/shortest-path-between-cells-in-matrix-code/#:~:text=Shortest%20path%20in%20matrix%20is,starts%20from%20the%20source%20node.
        // 
        int total = 0;
        // int passos12 = mapa01.caminhoMaisCurto(posicoes[0], posicoes[1], posicoes[2], posicoes[3]);
        // if (passos12 > 0) {
        //     System.out.println("Passos do 1 até o 2: " + passos12);
        //     total += passos12;
        // }
        // int passos23 = mapa01.caminhoMaisCurto(posicoes[2], posicoes[3], posicoes[4], posicoes[5]);
        // if (passos23 > 0) {
        //     System.out.println("Passos do 2 até o 3: " + passos23);
        //     total += passos23;//8 a menos
        // }
        // int passos34 = mapa01.caminhoMaisCurto(posicoes[4], posicoes[5], posicoes[6], posicoes[7]);
        // if (passos34 > 0) {
        //     System.out.println("Passos do 3 até o 4: " + passos34);
        //     total += passos34;
        // }
        // int passos45 = mapa01.caminhoMaisCurto(posicoes[6], posicoes[7], posicoes[8], posicoes[9]);
        // if (passos45 > 0) {
        //     System.out.println("Passos do 4 até o 5: " + passos45);
        //     total += passos45;
        // }
        // int passos56 = mapa01.caminhoMaisCurto(posicoes[8], posicoes[9], posicoes[10], posicoes[11]);
        // if (passos56 > 0) {
        //     System.out.println("Passos do 5 até o 6: " + passos56);
        //     total += passos56;
        // }
        // int passos67 = mapa01.caminhoMaisCurto(posicoes[10], posicoes[11], posicoes[12], posicoes[13]);
        // if (passos67 > 0) {
        //     System.out.println("Passos do 6 até o 7: " + passos67);
        //     total += passos67;
        // }
        // int passos78 = mapa01.caminhoMaisCurto(posicoes[12], posicoes[13], posicoes[14], posicoes[15]);
        // if (passos78 > 0) {
        //     System.out.println("Passos do 7 até o 8: " + passos78);
        //     total += passos78;
        // }
        // int passos89 = mapa01.caminhoMaisCurto(posicoes[14], posicoes[15], posicoes[16], posicoes[17]);
        // if (passos89 > 0) {
        //     System.out.println("Passos do 8 até o 9: " + passos89);
        //     total += passos89;
        // }
        // int passos91 = mapa01.caminhoMaisCurto(posicoes[16], posicoes[17], posicoes[0], posicoes[1]);
        // if (passos91 > 0) {
        //     System.out.println("Passos do 9 até o 1: " + passos91);
        //     total += passos91;
        // }
        // System.out.println("adicionar: " + mapa01.bfs(posicoes[2], posicoes[3], posicoes[6], posicoes[7]));
        // if (total != 0)
        //     System.out.println("total: " + total);
        // else   
        //     System.out.println("NAO TEM CAMINHO");
        // total = mapa01.caminhoTotal();
        total = mapa01.viajar();
        System.out.println(total);
        long fim = System.currentTimeMillis();
        System.out.println("TEMPO: " + (fim - inicio) + "ms");
        mapa01.getPosicoes();

    
    }
}
