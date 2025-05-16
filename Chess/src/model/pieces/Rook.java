package model.pieces;

import model.Coordinate;
import model.Piece;
import model.PieceColor;
import model.PieceType;

public class Rook extends Piece
{
    public Rook( PieceColor pieceColor )
    {
        super( PieceType.R, pieceColor );
    }


    @Override
    public boolean isValidMove(final Coordinate from, final Coordinate to, final Piece[][] chessBoard )
    {
        boolean isStraight = (from.getX() == to.getX() || from.getY() == to.getY());

        if (!isStraight)
        {
            return false;
        }

        return super.checkPos1ToPos2( from, to, chessBoard );
    }
}
