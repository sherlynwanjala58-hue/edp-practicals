import javax.swing.*;
import java.awt.*;

public class TimerAnimationDemo extends JFrame {
    private int x = 20;
    private int y = 90;
    private int dx = 4;
    private final Timer animationTimer;
    private final JPanel stagePanel;

    public TimerAnimationDemo() {
        setTitle("Week 6 - Swing Timer Animation");
        setSize(580, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        stagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(25, 118, 210));
                g2.fillOval(x, y, 44, 44);
            }
        };
        stagePanel.setBackground(Color.WHITE);

        // Swing Timer fires on the EDT every ~16 ms (~60 FPS)
        animationTimer = new Timer(16, e -> {
            x += dx;
            int maxX = Math.max(0, stagePanel.getWidth() - 44);
            if (x <= 0) {
                x = 0;
                dx = Math.abs(dx);
            } else if (x >= maxX && maxX > 0) {
                x = maxX;
                dx = -Math.abs(dx);
            }
            stagePanel.repaint();
        });

        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");
        JButton reverseBtn = new JButton("Reverse");

        startBtn.addActionListener(e -> animationTimer.start());
        stopBtn.addActionListener(e -> animationTimer.stop());
        reverseBtn.addActionListener(e -> dx = -dx);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        controls.add(startBtn);
        controls.add(stopBtn);
        controls.add(reverseBtn);

        add(stagePanel, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
        animationTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimerAnimationDemo().setVisible(true));
    }
}