package com.bbdr.chess;

import java.util.HashMap;

public abstract class Piece {
    public static final int COLOR_NONE = 0;
    public static final int COLOR_WHITE = 1;
    public static final int COLOR_BLACK = 2;
    
    public static final int RANK_NONE = 0;
    public static final int RANK_PAWN = 1;
    public static final int RANK_ROOK = 2;
    public static final int RANK_KNIGHT = 3;
    public static final int RANK_BISHOP = 4;
    public static final int RANK_QUEEN = 5;
    public static final int RANK_KING = 6;
    public static final String[] RANK_NAMES = new String[] {
        "UNDEFINED_RANK",
        "Pawn",
        "Rook",
        "Knight",
        "Bishop",
        "Queen",
        "King"
    };
    
    public static final String[] COLOR_NAMES = new String[] {
        "UNDEFINED_COLOR",
        "White",
        "Black"
    };
    
    // Public fields.
    
    // Current position within the board it is contained in.
    public final Position position = new Position(-1, -1);
    
    // Piece's color.
    public int color = COLOR_NONE;
    
    // A list of valid moves given any configuration.
    // TODO investigate SparseBooleanArray.
    public static HashMap<Integer, Boolean> validMoves = new HashMap<Integer, Boolean>();
    
    public abstract int getRank();
    
    /**
     * Returns true if the move to relative coordinates (relX, relY) is valid.
     * This method should be overridden for each subclass.
     * @param relX the relative coordinate X.
     * @param relY the relative coordinate Y.
     * @return true if the piece can move to relative coordinates (relX, relY).
     */
    public static boolean isValidMove(int relX, int relY) {
        return true;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public void moveTo(int x, int y) {
        position.x = x;
        position.y = y;
    }
    
    public String getColorName() {
        return RANK_NAMES[this.getRank()];
    }
    
    public String getRankName() {
        return COLOR_NAMES[this.getColor()];
    }
    
    @Override
    public String toString() {
        return this.getColorName() + " " + this.getRankName() + "@(" + position.toString() + ")";
    }
    
    static {
        // Move list: given some coordinates (x, y) relative to
        // this piece's position on the board, is there any
        // configuration that such a move will be valid?
        // This check is used as the first validation step because
        // it runs in O(1) time complexity.
        
        for (int x = -7; x <= 7; x++) {
            for (int y = -7; y <= 7; y++) {
                // Test if this move is valid in any configuration.
                validMoves.put(Position.getRelativeOffset(x, y), isValidMove(x, y));
            }
        }
    }
    
    public Piece(int x, int y) {
        moveTo(x, y);
    }
    
    public Piece() {
        
    }
}