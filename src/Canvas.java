import fill.Filler;
import fill.SeedFiller;
import model.Point;
import model.Polygon;
import rasterize.*;
import model.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Canvas {
    private final JFrame frame;
    private final JPanel panel;
    private RasterBufferImage raster;
    private LineRasterizer lineRasterizer;
    private Polygon polygon;
    private PolygonRasterizer polygonRasterizer;

    public Canvas(int width, int height) {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("PGRF1");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferImage(800, 600);
        lineRasterizer = new LineRasterizerGraphics(raster);
        //lineRasterizer = new LineRasterizerTrivial(raster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);
        polygon = new Polygon();

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                raster.present(g);
            }
        };

        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        panel.requestFocus();
        panel.requestFocusInWindow();

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                raster.clear();

                if(e.getButton() == MouseEvent.BUTTON1) {
                    if (polygon.getCount() == 4)
                        polygon.clearPoints();

                    polygon.addPoint(new Point(e.getX(), e.getY()));
                }

                polygonRasterizer.rasterize(polygon);

                if(e.getButton() == MouseEvent.BUTTON3) {
                    Filler seedFiller = new SeedFiller(raster, e.getX(), e.getY(),
                            0x00ff00, Color.black.getRGB());
                    seedFiller.fill();
                }

                panel.repaint();
            }
        });

//        panel.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                super.mouseDragged(e);
//
//                raster.clear();
//
//                Line line = new Line(width / 2, height / 2, e.getX(), e.getY());
//                lineRasterizer.rasterize(line);
//
//                panel.repaint();
//            }
//        });
    }

    public void start() {
        raster.clear();
        panel.repaint();
    }
}
