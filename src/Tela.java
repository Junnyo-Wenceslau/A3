import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Tela extends JFrame {
    private final List<CartaImagem> cartas = new ArrayList<>();
    private final List<CartaImagem> selecionadas = new ArrayList<>();
    private final JPanel painelCartas = new JPanel(new GridLayout(4, 4));

    public Tela() {
        super("🧠 Jogo da Memória");

        inicializarCartas();
        montarInterface();

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void inicializarCartas() {
        // Caminhos das imagens (pares)
        String[] imagens = {
            "img/carta1.gif", "img/carta2.gif", "img/carta3.gif", "img/carta4.gif",
            "img/carta5.gif", "img/carta6.gif", "img/carta7.gif", "img/carta8.gif"
        };

        List<String> listaImagens = new ArrayList<>();
        for (String img : imagens) {
            listaImagens.add(img);
            listaImagens.add(img); 
        }

        Collections.shuffle(listaImagens);

        for (String img : listaImagens) {
            CartaImagem carta = new CartaImagem(img);
            carta.getBotao().addActionListener(e -> clicarCarta(carta));
            cartas.add(carta);
        }
    }

    private void montarInterface() {
        for (CartaImagem carta : cartas) {
            painelCartas.add(carta.getBotao());
            carta.atualizarVisual();
        }

        JButton salvar = new JButton("💾 Salvar");
        JButton carregar = new JButton("📂 Carregar");

        salvar.addActionListener(e -> SalvarJogo.salvar(cartas, "salvo.txt"));
        carregar.addActionListener(e -> {
            SalvarJogo.carregar(cartas, "salvo.txt");
            for (CartaImagem c : cartas) {
                c.atualizarVisual();
            }
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(salvar);
        painelBotoes.add(carregar);

        this.setLayout(new BorderLayout());
        this.add(painelCartas, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);
    }

    private void clicarCarta(CartaImagem carta) {
        if (carta.isVirada() || carta.isEncontrada() || selecionadas.size() >= 2)
            return;

        carta.virar();
        carta.atualizarVisual();
        selecionadas.add(carta);

        if (selecionadas.size() == 2) {
            Timer timer = new Timer(1000, e -> verificarPar());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void verificarPar() {
        if (selecionadas.size() < 2) return;

        CartaImagem c1 = selecionadas.get(0);
        CartaImagem c2 = selecionadas.get(1);

        if (c1.getIdentificador().equals(c2.getIdentificador())) {
            c1.setEncontrada(true);
            c2.setEncontrada(true);
        } else {
            c1.virar();
            c2.virar();
        }

        c1.atualizarVisual();
        c2.atualizarVisual();
        selecionadas.clear();

        if (todasEncontradas()) {
            JOptionPane.showMessageDialog(this, "🎉 Parabéns, você venceu!");
        }
    }

    private boolean todasEncontradas() {
        for (CartaImagem carta : cartas) {
            if (!carta.isEncontrada()) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaJogo::new);
    }
}
