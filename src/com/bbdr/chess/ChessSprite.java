package com.bbdr.chess;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;


public class ChessSprite {
    /**
     * This class is designed to be a static
     * class for creating an array of bitmaps 
     * and intialising them.
     */
    
    // The array of bitmaps, accessed statically.
    public static final Bitmap[] bitmaps = new Bitmap[Piece.getFingerprint(Piece.RANK_MAXVAL, Piece.PLAYER_MAXVAL) + 1];
    
    // The source rectangle for canvas painting.
    public static final RectF rectSource = new RectF();
    // The destination rectangle for canvas painting.
    public static final RectF rectDestination = new RectF();
    
    /**
     * Checks if a bitmap for the sprite ID exists.
     * @param spriteID the sprite ID to check for.
     * @return true if a bitmap exists for the sprite ID.
     */
    public static boolean contains(int spriteID) {
        if (spriteID == -1 || bitmaps.length < spriteID || bitmaps[spriteID] == null) {
            return false;
        }
        return true;
    }
    
    /**
     * Initializes the static class. This requires a reference to the
     * resources and must be called when the Activity begins.
     * @param r the resources containing the bitmaps.
     */
    public static void initBitmaps(Resources r) {
        bitmaps[Piece.getFingerprint(Piece.RANK_PAWN, Piece.PLAYER_BLACK)] =
                BitmapFactory.decodeResource(r, R.drawable.pawn_black);
        bitmaps[Piece.getFingerprint(Piece.RANK_ROOK, Piece.PLAYER_BLACK)] =
                BitmapFactory.decodeResource(r, R.drawable.castle_black);
        bitmaps[Piece.getFingerprint(Piece.RANK_KNIGHT, Piece.PLAYER_BLACK)] =
                BitmapFactory.decodeResource(r, R.drawable.knight_black);
        bitmaps[Piece.getFingerprint(Piece.RANK_BISHOP, Piece.PLAYER_BLACK)] =
                BitmapFactory.decodeResource(r, R.drawable.bishop_black);
        bitmaps[Piece.getFingerprint(Piece.RANK_QUEEN, Piece.PLAYER_BLACK)] =
                BitmapFactory.decodeResource(r, R.drawable.queen_black);
        bitmaps[Piece.getFingerprint(Piece.RANK_KING, Piece.PLAYER_BLACK)] =
                BitmapFactory.decodeResource(r, R.drawable.king_black);
        bitmaps[Piece.getFingerprint(Piece.RANK_PAWN, Piece.PLAYER_WHITE)] =
                BitmapFactory.decodeResource(r, R.drawable.pawn_white);
        bitmaps[Piece.getFingerprint(Piece.RANK_ROOK, Piece.PLAYER_WHITE)] =
                BitmapFactory.decodeResource(r, R.drawable.castle_white);
        bitmaps[Piece.getFingerprint(Piece.RANK_KNIGHT, Piece.PLAYER_WHITE)] =
                BitmapFactory.decodeResource(r, R.drawable.knight_white);
        bitmaps[Piece.getFingerprint(Piece.RANK_BISHOP, Piece.PLAYER_WHITE)] =
                BitmapFactory.decodeResource(r, R.drawable.bishop_white);
        bitmaps[Piece.getFingerprint(Piece.RANK_QUEEN, Piece.PLAYER_WHITE)] =
                BitmapFactory.decodeResource(r, R.drawable.queen_white);
        bitmaps[Piece.getFingerprint(Piece.RANK_KING, Piece.PLAYER_WHITE)] =                         
                BitmapFactory.decodeResource(r, R.drawable.king_white);
        
        // The sample bitmap, so that we can get the width and height from it.
        Bitmap sampleBitmap = bitmaps[Piece.getFingerprint(Piece.RANK_PAWN, Piece.PLAYER_BLACK)];
        
        // Set the source and destination rectangles.
        // The source maps the sprite dimensions: (0, 0) to (width, height).
        rectSource.set(0F, 0F, sampleBitmap.getWidth(), sampleBitmap.getHeight());
        // The destination maps the canvas dimensions.
        // This will need to be overridden on a per-use basis.
        rectDestination.set(0F, 0F, sampleBitmap.getWidth(), sampleBitmap.getHeight());
    }
}
