package util;

import javax.swing.*;
import java.awt.*;

public class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField() {
        super();
    }

    public PlaceholderTextField(String text) {
        super(text);
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getText().isEmpty() && placeholder != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            // Calcula la posición del placeholder
            Insets insets = getInsets();
            FontMetrics fm = g2.getFontMetrics();
            int x = insets.left + 5; // Puedes ajustar la posición horizontal
            int y = getHeight() / 2 + fm.getAscent() / 2 - 2; // Centrado verticalmente
            g2.drawString(placeholder, x, y);
            g2.dispose();
        }
    }
}
