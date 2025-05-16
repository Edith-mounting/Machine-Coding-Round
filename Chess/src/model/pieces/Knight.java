package model.pieces;

import model.Coordinate;
import model.Piece;
import model.PieceColor;
import model.PieceType;

public class Knight extends Piece
{
    public Knight( PieceColor pieceColor )
    {
        super( PieceType.N, pieceColor );
    }


    @Override
    public boolean isValidMove( final Coordinate from, final Coordinate to, final Piece[][] chessBoard )
    {
        return Math.abs( from.getX() - to.getX() ) + Math.abs( from.getY() - to.getY() ) == 3;
    }
}
