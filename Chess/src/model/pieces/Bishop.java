package model.pieces;

import model.*;

public class Bishop extends Piece
{
    public Bishop( final PieceColor pieceColor )
    {
        super( PieceType.B, pieceColor );
    }


    @Override
    public boolean isValidMove(final Coordinate from, final Coordinate to, final Piece[][] chessBoard)
    {
        boolean isDiagonal = Math.abs(from.getY() - to.getY()) == Math.abs(from.getX() - to.getX());

        if (!isDiagonal)
        {
            return false;
        }
        return super.checkPos1ToPos2( from, to, chessBoard );
    }
}
