package rasterize;

import model.Line;
import model.Point;
import model.Polygon;

public class PolygonRasterizer {
    private LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizere) {
        this.lineRasterizer = lineRasterizere;
    }

    public void rasterize(Polygon polygon) {
        if(polygon.getCount() != 4)
            return;

        Point point1 = polygon.getPoint(0);
        Point point2 = polygon.getPoint(1);
        Point point3 = polygon.getPoint(2);
        Point point4 = polygon.getPoint(3);

        // spojit 1. s 2.
        lineRasterizer.rasterize(new Line(point1.getX(), point1.getY(), point2.getX(), point2.getY()));
        lineRasterizer.rasterize(new Line(point2.getX(), point2.getY(), point3.getX(), point3.getY()));
        lineRasterizer.rasterize(new Line(point3.getX(), point3.getY(), point4.getX(), point4.getY()));
        lineRasterizer.rasterize(new Line(point4.getX(), point4.getY(), point1.getX(), point1.getY()));
    }
}
