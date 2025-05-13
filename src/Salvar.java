import java.io.*;
import java.util.List;

public class Salvar {
    public static void salvar(List<CartaImagem> cartas, String caminho) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (CartaImagem carta : cartas) {
                bw.write(carta.getIdentificador() + ";" + carta.isEncontrada());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregar(List<CartaImagem> cartas, String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            int i = 0;
            while ((linha = br.readLine()) != null && i < cartas.size()) {
                String[] partes = linha.split(";");
                if (partes.length == 2 &&
                    partes[0].equals(cartas.get(i).getIdentificador())) {
                    cartas.get(i).setEncontrada(Boolean.parseBoolean(partes[1]));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}