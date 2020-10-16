package logic;

import java.awt.*;

public class Field {
    private Color color;
    private int value;

    public Field(){
        color = Color.white;
        value = 0;
    }

    public Field(Color color){
        this.color = color;
        value = 0;
    }

    public Field(Color color, int value){
        this.color = color;
        this.value= value;
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
