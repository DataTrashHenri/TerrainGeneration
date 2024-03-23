package com.mygdx.game;

public class PerlinNoise {
    // Diese Funktion interpoliert zwischen a0 bei x=0 und a1 bei x=1. x muss im Intervall [0, 1] liegen.
    public static float interpolate(float a0, float a1, float x) {
        float g; // Gewicht für die Interpolation
        //g = x; // lineare Interpolation; ergibt stetiges, aber nicht differenzierbares Rauschen
        g = (3.0f - x * 2.0f) * x * x; // kubische Interpolation mit dem Polynom 3 * x^2 - 2 * x^3
        //g = ((x * (x * 6.0f - 15.0f) + 10.0f) * x * x * x); // Interpolation mit dem Polynom 6 * x^5 - 15 * x^4 + 10 * x^3
        return (a1 - a0) * g + a0;
    }

    // Datentyp für zweidimensionale Vektoren
    public static class Vector2 {
        float x, y;

        public Vector2(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    // Diese Funktion erzeugt den pseudozufälligen Gradientenvektor für den Gitterpunkt (ix,iy)
    public static Vector2 randomGradient(int ix, int iy) {
        final int w = Integer.SIZE;
        final int s = w / 2;
        int a = ix, b = iy;
        a *=  3284157443L;
        b ^= a << s | a >>> w - s;
        b *= 1911520717;
        a ^= b << s | b >>> w - s;
        a *= 2048419325;
        float random = a * (float) (Math.PI / (~(int) (~0 >>> 1))); // Erzeugt eine Zufallszahl im Intervall [0, 2 * Pi]
        return new Vector2((float) Math.cos(random), (float) Math.sin(random)); // v hat den Betrag 1
    }

    // Diese Funktion berechnet das Skalarprodukt von Abstandsvektor und Gradientenvektor
    public static float dotGridGradient(int ix, int iy, float x, float y) {
        Vector2 grad = randomGradient(ix, iy);
        // Berechne den Abstandsvektor:
        float dx = x - (float) ix;
        float dy = y - (float) iy;
        return dx * grad.x + dy * grad.y; // Skalarprodukt
    }

    // Diese Funktion berechnet den Wert des Perlin noise für den Punkt (x, y)
    // Das Ergebnis liegt im Intervall [-1/sqrt(2), 1/sqrt(2)]
    public static float perlin(float x, float y) {
        // Bestimme die Koordinaten der vier Ecken der Gitterzelle:
        int x0 = (int) Math.floor(x);
        int x1 = x0 + 1;
        int y0 = (int) Math.floor(y);
        int y1 = y0 + 1;

        // Bestimme die Abstände von den Gitterpunkten für die Interpolation:
        float sx = x - (float) x0;
        float sy = y - (float) y0;

        // Interpoliere zwischen den Skalarprodukten an den vier Ecken:
        float n0, n1, ix0, ix1;
        n0 = dotGridGradient(x0, y0, x, y);
        n1 = dotGridGradient(x1, y0, x, y);
        ix0 = interpolate(n0, n1, sx);
        n0 = dotGridGradient(x0, y1, x, y);
        n1 = dotGridGradient(x1, y1, x, y);
        ix1 = interpolate(n0, n1, sx);

        return interpolate(ix0, ix1, sy);
    }

//    public static void main(String[] args) {
//        // Example usage:
//        float result = perlin(0.5f, 0.5f);
//        System.out.println("Perlin noise value at (0.5, 0.5): " + result);
//    }
}
