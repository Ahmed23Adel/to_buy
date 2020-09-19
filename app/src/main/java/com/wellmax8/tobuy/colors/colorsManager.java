package com.wellmax8.tobuy.colors;

import android.widget.RelativeLayout;

public class colorsManager {
    private color color;
    private int lastPressedColor = color.RED;
    RelativeLayout rRed, rYellow, rBlue, rPurple, rGreen;
    RelativeLayout lastPressedLayout;

    public colorsManager(RelativeLayout rRed, RelativeLayout rYellow, RelativeLayout rBlue, RelativeLayout rPurple, RelativeLayout rGreen) {
        color=new color();
        this.rRed = rRed;
        this.rYellow = rYellow;
        this.rBlue = rBlue;
        this.rPurple = rPurple;
        this.rGreen = rGreen;
    }

    public void press(int pressedColor) {
        if (isPressedColorValid(pressedColor)) {
            lastPressedColor = pressedColor;

            returnLastPressedNormal();
            RelativeLayout relativeLayout = determineLayout(lastPressedColor);
            setPaddingToLayout(relativeLayout);
            lastPressedLayout=relativeLayout;
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
        relativeLayout.setPadding(20, 20, 20, 20);
    }

    public color getColorInstance(){
        color.setChosenColor(lastPressedColor);
        return color;
    }
    public int getChosenColor(){
        return lastPressedColor;
    }

    private void setZeroPaddingToLayout(RelativeLayout relativeLayout) {
        relativeLayout.setPadding(0, 0, 0, 0);
    }
    private void returnLastPressedNormal() {
        if (lastPressedLayout != null) {
            setZeroPaddingToLayout(lastPressedLayout);
        }
    }
}
