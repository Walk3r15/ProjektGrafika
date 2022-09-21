import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Canvas {
    private final JFrame frame;
    private final JPanel panel;
    private final BufferedImage img;
    public Canvas(int width, int height){
        int lokace;
        int stredv;
        int streds;

        stredv = height / 2;
        streds = width / 2;

        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("Malovani 2D : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        img.setRGB(10, 10, 0xff0000);

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        panel.requestFocus();
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_P);
                {
                    int vyska = 400;
                    img.setRGB(vyska, 25, 0xff0000);
                    panel.repaint();
                    vyska++;
                }
            }
        });
    }
}
