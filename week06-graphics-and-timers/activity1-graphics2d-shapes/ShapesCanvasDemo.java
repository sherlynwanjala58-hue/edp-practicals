import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

public class ShapesCanvasDemo extends JFrame {
    public ShapesCanvasDemo() {
        setTitle("Week 6 - Graphics2D Shapes & Strokes");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Rounded rectangle card
                g2.setColor(new Color(235, 244, 255));
                g2.fill(new RoundRectangle2D.Double(30, 30, 240, 140, 24, 24));
                g2.setColor(new Color(30, 90, 175));
                g2.setStroke(new BasicStroke(2.5f));
                g2.draw(new RoundRectangle2D.Double(30, 30, 240, 140, 24, 24));

                g2.setFont(new Font("Arial", Font.BOLD, 15));
                g2.drawString("Event Programming Canvas", 48, 62);
                g2.setFont(new Font("Arial", Font.PLAIN, 13));
                g2.drawString("Custom painting on JPanel", 48, 88);

                // Gradient circle
                GradientPaint gp = new GradientPaint(320, 40, Color.ORANGE, 470, 190, Color.RED);
                g2.setPaint(gp);
                g2.fill(new Ellipse2D.Double(320, 40, 150, 150));

                // Dashed guide line
                float[] dashPattern = {8f, 6f};
                g2.setColor(Color.DARK_GRAY);
                g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10f, dashPattern, 0f));
                g2.draw(new Line2D.Double(30, 220, 550, 220));

                // Bar chart preview
                int[] values = {65, 90, 50, 110};
                Color[] colors = {
                    new Color(66, 133, 244),
                    new Color(52, 168, 83),
                    new Color(251, 188, 5),
                    new Color(234, 67, 53)
                };
                for (int i = 0; i < values.length; i++) {
                    int barHeight = values[i];
                    int x = 60 + i * 110;
                    int y = 350 - barHeight;
                    g2.setColor(colors[i]);
                    g2.fillRect(x, y, 65, barHeight);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, y, 65, barHeight);
                    g2.drawString("W" + (i + 1) + ": " + values[i], x + 8, 370);
                }
            }
        };

        drawPanel.setBackground(Color.WHITE);
        add(drawPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapesCanvasDemo().setVisible(true));
    }
}