package com.bbdr.chess;

import android.util.AttributeSet;
import android.view.View;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

public class ChessPieceView extends View {
    
    /**
    * This class creates a custom View that can be placed
    * on the layout to implement custom drawing behavior. 
    * In this case, the View will draw a specific bitmap
    * (Queen, King, etc.) depending on the View's state.
    */
    public static final int SIZE_TILE = 35;
    protected static final Matrix pieceTransMatrix = new Matrix();
    
    protected Piece piece = null;
    protected Position position = new Position(-1, -1);

    // the view for a chess piece
    protected int pieceID = -1;

    public void update() {
        if (this.pieceID != piece.getFingerprint()) {
            this.setSpriteID(piece.getFingerprint());
        }
        if (this.position.x != piece.position.x || this.position.y != piece.position.y) {
            if (piece.position.x == -1 && piece.position.y == -1) {
                this.setSpriteVisibility(false);
            }
            this.setSpritePosition(piece.position);
        }
    }
    
    @Override
    public void onDraw(Canvas canvas) {
  
        if (this.isInEditMode()) {
            return;
        }
        // We don't want to do anything if the piece ID does
        // not map to a Bitmap. Otherwise, uh, exception.
        if (pieceID == -1 || ChessSprite.bitmaps.length < pieceID || ChessSprite.bitmaps[pieceID] == null) {
            // This should NOT have happened.
            // It's probably Bailey's fault.
            // Blame him if you cannot figure it out.
            // Oh. Can't throw exceptions in this method.
            //throw new BaileysFaultException("You done fucked up");
            return;
        }
        
        // Set the rectangle that we are drawing to.
        //rectDestination.set(0F, 0F, this.getMeasuredWidth(), this.getMeasuredHeight());
        ChessSprite.rectDestination.set(0F, 0F, getPixels(SIZE_TILE), getPixels(SIZE_TILE));
        
        // Construct the matrix that transforms the Bitmap into our tile area.
        pieceTransMatrix.setRectToRect(ChessSprite.rectSource, ChessSprite.rectDestination,
                Matrix.ScaleToFit.END);
        
        // Draw the bitmap to the canvas according to the transformation matrix.
        canvas.drawBitmap(ChessSprite.bitmaps[pieceID], pieceTransMatrix, null);
    
        
    }

    public void setSpriteVisibility(boolean val) {
        this.setVisibility(val ? VISIBLE : GONE);
    }
    
    public void setSpritePosition(Position pos) {
        this.position.x = pos.x;
        this.position.y = pos.y;
        this.setX(getPixels(pos.x * SIZE_TILE));
        this.setY(getPixels(pos.y * SIZE_TILE));
    
        //Resources r = getResources();
    }
    
    public void setSpriteID(int id) {
        // Check that the piece ID is even different.
        if (this.pieceID != id) {
            // It's different. Overwrite the ID and mark this view
            // for re-rendering.
            this.pieceID = id;
            this.invalidate();
        }
        // If it's not different, then we don't need to change anything.
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
        this.update();
    }
    
 

    protected int getPixels(int dp) {
        return (int) (getResources().getDisplayMetrics().density * dp);
    }

    @Override
    public void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        // sets the measurements of the view
        this.setMeasuredDimension(measureWidth(measureWidthSpec),
                measureHeight(measureHeightSpec));
    }

    // make the piece size match tile size
    public int measureWidth(int spec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(spec);
        int specSize = MeasureSpec.getSize(spec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getPixels(SIZE_TILE);
        }
        return result;
    }

    // make the piece size match tile size
    public int measureHeight(int spec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(spec);
        int specSize = MeasureSpec.getSize(spec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getPixels(SIZE_TILE);
        }
        return result;
    }

    public ChessPieceView(Context context) {
        // This constructor is used by our activity.
        super(context);
    }

    // AttributeSet of properties specified in the xml resource file. This is
    // called to intialise a view from a a layout file
    public ChessPieceView(Context context, AttributeSet attrs) {
        // This constructor is used by the UI designer.
        super(context, attrs);
    }
}
