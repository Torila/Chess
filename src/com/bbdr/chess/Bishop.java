package com.bbdr.chess;

public class Bishop extends Piece implements Renderable, Moveable {

    @Override
    public boolean isValidMove(int relX, int relY) {
        // Valid moves for the Bishop:
        // (1)Diagonal
        
        // Get the absolute values for relX and relY
        relX = Math.abs(relX);
        relY = Math.abs(relY);
        // Allow moving on the diagonal axes
        if (relX == relY) {
          //Check if bishop is in the same place
            return (relX != 0);
        }
        return false;
    }

    @Override
    public boolean canMoveTo(int x, int y, Board board) {
        // TODO
        return true;
    }

    @Override
    public void draw() {
        // TODO
    }

    @Override
    public int getRank() {
        return Piece.RANK_BISHOP;
    }

    public Bishop(int x, int y, int player) {
        super(x, y, player);
    }

    public Bishop(int x, int y) {
        super(x, y);
    }

    public Bishop() {
        super();
    }
}
