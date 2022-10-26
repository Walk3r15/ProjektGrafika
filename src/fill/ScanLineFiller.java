package fill;

import model.Edge;
import model.Point;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.PolygonRasterizer;

import java.util.ArrayList;
import java.util.List;

public class ScanLineFiller implements Filler {

    private final LineRasterizer lineRasterizer;
    private final PolygonRasterizer polygonRasterizer;
    private final Polygon polygon;

    public ScanLineFiller(LineRasterizer lineRasterizer, PolygonRasterizer polygonRasterizer, Polygon polygon) {
        this.lineRasterizer = lineRasterizer;
        this.polygonRasterizer = polygonRasterizer;
        this.polygon = polygon;
    }

    @Override
    public void fill() {
        scanLine();
    }
    // procházenívodů for cyklem a ukládání lichého a sudého
    private void scanLine(){
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < polygon.getCount(); i++)
        {
            Point p1 = polygon.getPoint(i);
            int index = (i + 1) % polygon.getCount();
            Point p2 = polygon.getPoint(index);

            Edge edge = new Edge(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            if(edge.isHorizontal())
            {
                return;
            }
            else
            {
                edge.orientate();
                edges.add(edge);
            }
            //hledám yMin a yMax
            int yMin = polygon.getPoint(0).getY();
            int yMax = yMin;
            for (int h = 0; h < polygon.getCount(); h++)
            {
                if(polygon.getPoint(h).getY() > yMax)
                {
                    yMax = polygon.getPoint(h).getY();
                }
                if(polygon.getPoint(h).getY() < yMax)
                {
                    yMin = polygon.getPoint(h).getY();
                }
            }
            //Pro všechna y od yMin po yMax
            for (int y = yMin; y <= yMax; y++)
            {
                //sezna pruseciku list<intger>
                //pro vsechny hrany
                //existuje pruseik?
                //ano spocitam pruseik
                //ulozime do seznamu pruseciku
                //setridime je podle x treba bubblesort
                //spojit prusciky linou, linchy se sudym
            }

            //vykreslit polygon - hranici
        }
    }
}
