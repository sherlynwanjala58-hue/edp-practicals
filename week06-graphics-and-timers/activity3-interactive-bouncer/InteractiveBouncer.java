import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InteractiveBouncer extends JFrame {
    private double ballX = 120;
    private double ballY = 80;
    private double vx = 4.5;
    private double vy = 3.5;
    private final int radius = 22;
    private Color ballColor = new Color(211, 47, 47);
    private final Timer timer;
    private final JPanel arena;

    public InteractiveBouncer() {
        setTitle("Week 6 - Interactive Bouncer (Click Arena to Reposition)");
        setSize(620, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        arena = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(ballColor);
                g2.fillOval((int) ballX, (int) ballY, radius * 2, radius * 2);
                g2.setColor(Color.DARK_GRAY);
                g2.drawString("Click anywhere inside to launch ball from that point", 14, 22);
            }
        };
        arena.setBackground(new Color(250, 250, 250));

        arena.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ballX = e.getX() - radius;
                ballY = e.getY() - radius;
                arena.repaint();
            }
        });

        timer = new Timer(15, e -> {
            int w = arena.getWidth();
            int h = arena.getHeight();
            int diameter = radius * 2;
            if (w <= diameter || h <= diameter) {
                return;
            }

            ballX += vx;
            ballY += vy;

            if (ballX < 0) {
                ballX = 0;
                vx = Math.abs(vx);
            } else if (ballX > w - diameter) {
                ballX = w - diameter;
                vx = -Math.abs(vx);
            }

            if (ballY < 0) {
                ballY = 0;
                vy = Math.abs(vy);
            } else if (ballY > h - diameter) {
                ballY = h - diameter;
                vy = -Math.abs(vy);
            }

            arena.repaint();
        });

        JButton toggleBtn = new JButton("Pause / Resume");
        JButton colorBtn = new JButton("Change Color");
        Color[] palette = {
            new Color(211, 47, 47),
            new Color(25, 118, 210),
            new Color(56, 142, 60),
            new Color(123, 31, 162)
        };
        final int[] colorIndex = {0};

        toggleBtn.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
        });

        colorBtn.addActionListener(e -> {
            colorIndex[0] = (colorIndex[0] + 1) % palette.length;
            ballColor = palette[colorIndex[0]];
            arena.repaint();
        });

        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        bottomBar.add(toggleBtn);
        bottomBar.add(colorBtn);

        add(arena, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InteractiveBouncer().setVisible(true));
    }
}