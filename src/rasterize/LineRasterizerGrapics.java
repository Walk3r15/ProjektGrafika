package rasterize;


import java.awt.*;

public class LineRasterizerGraphics extends LineRasterizer{

    public LineRasterizerGraphics(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        Graphics g = ((RasterBufferImage)raster).getImg().getGraphics();
        g.setColor(new Color(0xff0000));
        g.drawLine(x1, y1, x2, y2);
    }
}
