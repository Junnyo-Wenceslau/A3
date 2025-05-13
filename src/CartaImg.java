import javax.swing.*;

public class CartaImg extends Carta {
    private String caminhoImagem;
    private JButton botao;

    public CartaImg(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
        this.botao = new JButton(new ImageIcon("img/back.png"));
    }

    public JButton getBotao() {
        return botao;
    }

    public void atualizarVisual() {
        if (virada || encontrada) {
            botao.setIcon(new ImageIcon(caminhoImagem));
        } else {
            botao.setIcon(new ImageIcon("img/back.png"));
        }
    }

    @Override
    public String getIdentificador() {
        return caminhoImagem;
    }
}