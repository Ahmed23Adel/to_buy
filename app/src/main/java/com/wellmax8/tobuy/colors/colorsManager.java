package com.wellmax8.tobuy.colors;

import android.widget.RelativeLayout;

public class colorsManager {
    private color color;
    private int lastPressedColor = color.RED;
    RelativeLayout rRed, rYellow, rBlue, rPurple, rGreen;

    public colorsManager(RelativeLayout rRed, RelativeLayout rYellow, RelativeLayout rBlue, RelativeLayout rPurple, RelativeLayout rGreen) {
        this.rRed = rRed;
        this.rYellow = rYellow;
        this.rBlue = rBlue;
        this.rPurple = rPurple;
        this.rGreen = rGreen;
    }

    public void press(int pressedColor) {
        if (isPressedColorValid(pressedColor)) {
            lastPressedColor = pressedColor;

            RelativeLayout relativeLayout = determineLayout(lastPressedColor);
            setPaddingToLayout(relativeLayout);
        }else{
            throw new IllegalArgumentException("colors is out of bounds it should be within "+color.getMIN_INT_COLOR()+" and"+color.getMAX_INT_COLOR());        }
    }

    private boolean isPressedColorValid(int pressedColor) {
        if (pressedColor < color.getMIN_INT_COLOR() || pressedColor > color.getMAX_INT_COLOR()) {
            return false;
        } else {
            return true;
        }
    }

    private RelativeLayout determineLayout(int pressedColor) {
        if (pressedColor == color.RED) {
            return rRed;
        } else if (pressedColor == color.YELLOW) {
            return rYellow;
        } else if (pressedColor == color.BLUE) {
            return rBlue;
        } else if (pressedColor == color.PURPLE) {
            return rPurple;
        } else if (pressedColor == color.GREEN) {
            return rGreen;
        }
        return null;
    }

    public void setPaddingToLayout(RelativeLayout relativeLayout) {
        relativeLayout.setPadding(10, 10, 10, 10);
    }

    public color getColorInstance(){
        color.setChosenColor(lastPressedColor);
        return color;
    }
}
