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
        // 1. načtu barvu z pixelu
        int pixelColor = raster.getPixel(x, y);
        // Je barva pixelu stejná jako barva pozadí?
        // Pokud ne -> končím
        if (pixelColor != backgroundColor)
            return;

        // Pokud je -> pokračuju
        // obarvím pixel barvou fillColor
        raster.setPixel(x, y, fillColor);
        // rekurzivně zavolám metodu pro sousedy
        seedFill(x, y - 1);
        seedFill(x, y + 1);
        seedFill(x + 1, y);
        seedFill(x - 1, y);
    }
}
