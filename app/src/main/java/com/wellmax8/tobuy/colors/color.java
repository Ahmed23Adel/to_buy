package com.wellmax8.tobuy.colors;

import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.Exceptions.colorNotSpecifiedException;

public class color {
    public static int RED=0;
    public static int YELLOW=1;
    public static int BLUE=2;
    public static int PURPLE=3;
    public static int GREEN=4;

    private int COLOR_NOT_SPECIFIED=-1;
    private int MAX_INT_COLOR=GREEN;
    private int MIN_INT_COLOR=RED;
    private int chosenColor=COLOR_NOT_SPECIFIED;

    private String[] colorsNames= new String[]{"RED","YELLOW","BLUE","PURPLE","GREEN"};
    private Integer[] colorsValues= new Integer[]{R.color.red,R.color.yellow,R.color.blue,R.color.purple,R.color.green};
    private String[] colorsValuesHEX= new String[]{"#BF6262","#E7BF74","#4E84BF","#808EDF","#6CB1AC"};


    public void setChosenColor(int chosenColor) throws IllegalArgumentException{
        if (chosenColor<MIN_INT_COLOR||chosenColor>MAX_INT_COLOR){
            throw new IllegalArgumentException("your integer must be within range "+0+" and "+MAX_INT_COLOR);
        }
        this.chosenColor = chosenColor;
    }

    public int getChosenColor() throws colorNotSpecifiedException {
        if (chosenColor==COLOR_NOT_SPECIFIED){
            throw new colorNotSpecifiedException();
        }
        return chosenColor;
    }

    public String getColoHEX() throws colorNotSpecifiedException{
        if (chosenColor==COLOR_NOT_SPECIFIED){
            throw new colorNotSpecifiedException();
        }
        return colorsValuesHEX[chosenColor];
    }

    public int getChosenColorID() throws colorNotSpecifiedException{
        if (chosenColor==COLOR_NOT_SPECIFIED){
            throw new colorNotSpecifiedException();
        }
        return colorsValues[chosenColor];
    }

    public int getMAX_INT_COLOR() {
        return MAX_INT_COLOR;
    }

    public int getMIN_INT_COLOR() {
        return MIN_INT_COLOR;
    }
}
