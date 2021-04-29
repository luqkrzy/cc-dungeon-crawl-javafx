package com.codecool.dungeoncrawl.gui;

import javafx.scene.canvas.GraphicsContext;

public abstract class Display {

    protected final GraphicsContext context;
    protected final double x;
    protected final double y;

    public Display(GraphicsContext context, double x, double y) {
        this.context = context;
        this.x = x;
        this.y = y;
    }


}
