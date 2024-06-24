package com.acme.tracktorim.model;

public class CumulateNum {
    private int currentNum = 0;

    public CumulateNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }
}
