package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class Block {
    private Color texture;
    public Block(Texture texture) {
        switch (texture) {
            case GRASS:
                this.texture =Color.GREEN;
                break;
            case DIRT:
                this.texture =Color.BROWN;
                break;
            case STONE:
                this.texture =Color.GRAY;
                break;
            case SNOW:
                this.texture=Color.WHITE;
                break;
            case WATER:
                this.texture=Color.BLUE;
                break;
            case SAND:
                this.texture=Color.YELLOW;
                break;
            default:
                this.texture =Color.RED;
        }

    }
    public Color getTexture(){
        return texture;
    }
}
