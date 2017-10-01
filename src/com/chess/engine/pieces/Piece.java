package com.chess.engine.pieces;

/*Karanpreet on 30 September at 15:55*/

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove = false;
    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public int getPiecePosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

}
