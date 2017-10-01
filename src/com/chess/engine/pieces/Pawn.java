package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINTE = {8, 16};

    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINTE){
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection()*currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new Move.MajorMov(board, this, candidateDestinationCoordinate));
            }else if(currentCandidateOffset == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.piecePosition])
                    && (this.getPieceAlliance().isBlack()
                    || (BoardUtils.SEVENTH_ROW[this.piecePosition]) && this.getPieceAlliance().isWhite())){

                    final int behindCandidateDestination = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                    if(!board.getTile(behindCandidateDestination).isTileOccupied()
                            && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        legalMoves.add(new Move.MajorMov(board, this, candidateDestinationCoordinate));
                        }
                    }
            }

        return legalMoves;
    }
}
