package model;

import java.awt.*;

public class Hexagon extends Polygon{

    private Color color;

    public Hexagon(int[] xpoints, int[] ypoints, Color color){
        super(xpoints, ypoints, 6);
        this.color = color;
    }

    public final void paint(Graphics g){
        g.setColor(color);
        g.fillPolygon(this);
        Color color = this.color;
        g.setColor(Color.BLACK);
        g.drawPolygon(this);
        setColor(color);
    }

    public final void setColor(Color color){
        this.color = color;
    }
    
    public int[] getXpoints(){
        return xpoints;
    }

    public int[] getYpoints(){
        return ypoints;
    }
}
