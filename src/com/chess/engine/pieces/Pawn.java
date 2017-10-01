package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection()*currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new Move.MajorMov(board, this, candidateDestinationCoordinate));
            }else if(currentCandidateOffset == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.piecePosition])
                    && (this.pieceAlliance.isBlack()
                    || (BoardUtils.SEVENTH_ROW[this.piecePosition]) && this.pieceAlliance.isWhite())){

                    final int behindCandidateDestination = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                    if(!board.getTile(behindCandidateDestination).isTileOccupied()
                            && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        //Needs work
                        legalMoves.add(new Move.MajorMov(board, this, candidateDestinationCoordinate));
                    }
            }else if(currentCandidateOffset == 7
                    && !((BoardUtils.EIGHTH_COLUMN[piecePosition] && this.pieceAlliance.isWhite())
                    || (BoardUtils.FIRST_COLUMN[piecePosition] && this.pieceAlliance.isBlack()))){

                    if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                            legalMoves.add(new Move.MajorMov(board, this, candidateDestinationCoordinate));
                            //Needs work
                        }
                    }
            }else if(currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[piecePosition] && this.pieceAlliance.isWhite())
                            || (BoardUtils.EIGHTH_COLUMN[piecePosition] && this.pieceAlliance.isBlack()))){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        legalMoves.add(new Move.MajorMov(board, this, candidateDestinationCoordinate));
                        //Needs work
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    public String toString(){
        return PieceType.PAWN.toString();
    }
}
