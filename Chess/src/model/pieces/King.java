package model.pieces;

import model.Coordinate;
import model.Piece;
import model.PieceColor;
import model.PieceType;

public class King extends Piece
{
    public King( final PieceColor pieceColor )
    {
        super( PieceType.K, pieceColor );
    }


    @Override
    public boolean isValidMove(final Coordinate from, final Coordinate to, final Piece[][] chessBoard )
    {
        if (Math.abs( from.getX() - to.getX() ) + Math.abs( from.getY() - to.getY() ) == 1)
        {
            return true;
        }
        return (Math.abs( from.getX() - to.getX() ) == 1) && (Math.abs( from.getY() - to.getY() ) == 1);
    }
}
