package model.pieces;

import model.Coordinate;
import model.Piece;
import model.PieceColor;
import model.PieceType;

public class Queen extends Piece
{
    public Queen( PieceColor pieceColor )
    {
        super( PieceType.Q, pieceColor );
    }


    @Override
    public boolean isValidMove( final Coordinate from, final Coordinate to, final Piece[][] chessBoard )
    {
        // Check move direction
        boolean isDiagonal = Math.abs(from.getY() - to.getY()) == Math.abs(from.getX() - to.getX());
        boolean isStraight = (from.getY() == to.getY() || from.getX() == to.getX());

        if ( !isDiagonal && !isStraight )
        {
            return false;
        }
        return super.checkPos1ToPos2( from, to, chessBoard );
    }
}
