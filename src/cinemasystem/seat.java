/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

/**
 *
 * @author Dell
 */
public class seat {
    private char row;
    private int column;
    private boolean status;

    public seat(char row, int column, boolean status) {
        this.row = row;
        this.column = column;
        this.status = status;
    }

    public seat() {
    }
    
    

    public void setRow(char row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public char getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
