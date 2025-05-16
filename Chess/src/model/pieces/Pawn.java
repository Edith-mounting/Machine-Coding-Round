package model.pieces;

import model.Coordinate;
import model.Piece;
import model.PieceColor;
import model.PieceType;

public class Pawn extends Piece
{
    public Pawn( PieceColor pieceColor )
    {
        super( PieceType.P, pieceColor );
    }


    @Override
    public boolean isValidMove(final Coordinate from, final Coordinate to, final Piece[][] chessBoard )
    {
        PieceColor pawnColor = chessBoard[from.getY()][from.getX()].getPieceColor();
        if ( pawnColor.equals( PieceColor.B ) )
        {
            if (from.getY() == 6 && to.getY() == from.getY() - 2)
            {
                return true;
            }
            if (to.getY() == from.getY() - 1)
            {
                return (to.getX() == from.getX()) || (chessBoard[to.getY()][to.getX()] != null && Math.abs(to.getX() - from.getX()) == 1);
            }
            else {
                return false;
            }
        }
        else
        {
            if (from.getY() == 1 && to.getY() == 3)
            {
                return true;
            }
            if (to.getY() == from.getY() + 1)
            {
                return (to.getX() == from.getX()) || (chessBoard[to.getY()][to.getX()] != null && Math.abs(to.getX() - from.getX()) == 1);
            }
            else {
                return false;
            }
        }
    }
}
