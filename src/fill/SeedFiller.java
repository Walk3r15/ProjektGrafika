package fill;
import rasterize.Raster;

public class SeedFiller implements Filler {
    private final Raster raster;
    private final int x, y;
    private final int fillColor, backgroundColor;
    public SeedFiller(Raster raster, int x, int y, int fillColor, int backgroundColor) {
        this.raster = raster;
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        this.backgroundColor = backgroundColor;
    }
    @Override
    public void fill() {
        seedFill(x, y);
    }
    private void seedFill(int x, int y) {
        int pixelColor = raster.getPixel(x, y); //načte barvu z pix
        if (pixelColor != backgroundColor) //porovnání barvy - pokračuje nebo to nechá bty
            return;
        raster.setPixel(x, y, fillColor); //pokud ano pokračuje
        seedFill(x, y - 1); //bod nahoru
        seedFill(x, y + 1); //bod dolu
        seedFill(x + 1, y); //bod doprava
        seedFill(x - 1, y); //bod doleva
    }
}
