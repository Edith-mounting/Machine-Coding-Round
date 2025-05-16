package model;


public abstract class Piece
{
    private PieceType pieceType;
    private PieceColor pieceColor;

    public Piece( final PieceType pieceType, final PieceColor pieceColor )
    {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public PieceColor getPieceColor()
    {
        return this.pieceColor;
    }


    public PieceType getPieceType()
    {
        return this.pieceType;
    }


    public boolean isValidMove( final Coordinate from, final Coordinate to, final Piece[][] chessBoard )
    {
        return true;
    }


    public boolean checkPos1ToPos2(  final Coordinate from, final Coordinate to, final Piece[][] chessBoard  )
    {
        int rowStep = Integer.compare( to.getY(), from.getY() );
        int colStep = Integer.compare( to.getX(), from.getX() );

        int currRow = from.getY() + rowStep;
        int currCol = from.getX() + colStep;
        while (currRow != to.getY() && currCol != to.getX())
        {
            if (chessBoard[currRow][currCol] != null)
            {
                return false;
            }
            currRow += rowStep;
            currCol += colStep;
        }
        return true;
    }
}
