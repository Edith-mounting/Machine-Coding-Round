package model;

import model.pieces.*;

import java.util.Scanner;

public class ChessBoard
{
    private Integer size;
    private Piece[][] chessBoard;
    private String INVALID_MOVE = "INVALID MOVE, PLEASE PLAY AGAIN";

    public ChessBoard( final Integer size )
    {
        this.size = size;
        chessBoard = new Piece[size][size];

        initializeBoard();
    }


    private void initializeBoard()
    {
        // Initialize pawns.
        for (int i = 0; i < size; i++) // Columns
        {
            chessBoard[1][i] = new Pawn( PieceColor.W);
            chessBoard[6][i] = new Pawn( PieceColor.B);
        }

        // Initialize other white elements.
        chessBoard[0][0] = new Rook( PieceColor.W);
        chessBoard[0][7] = new Rook( PieceColor.W);
        chessBoard[0][1] = new Knight( PieceColor.W);
        chessBoard[0][6] = new Knight( PieceColor.W);
        chessBoard[0][2] = new Bishop( PieceColor.W);
        chessBoard[0][5] = new Bishop( PieceColor.W);
        chessBoard[0][4] = new King( PieceColor.W);
        chessBoard[0][3] = new Queen( PieceColor.W);

        // Initialize other black elements.
        chessBoard[7][0] = new Rook( PieceColor.B);
        chessBoard[7][7] = new Rook( PieceColor.B);
        chessBoard[7][1] = new Knight( PieceColor.B);
        chessBoard[7][6] = new Knight( PieceColor.B);
        chessBoard[7][2] = new Bishop( PieceColor.B);
        chessBoard[7][5] = new Bishop( PieceColor.B);
        chessBoard[7][4] = new King( PieceColor.B);
        chessBoard[7][3] = new Queen( PieceColor.B);
    }


    public void startGame()
    {
        boolean isWhiteMove = true;
        Scanner scanner = new Scanner( System.in );
        while (true)
        {
            String from = scanner.next();
            if ( "exit".equals( from ) )
            {
                return;
            }
            String to = scanner.next();

            Coordinate pos1 = new Coordinate( from.charAt(0) - 'a', from.charAt(1) - '1' );
            Coordinate pos2 = new Coordinate( to.charAt(0) - 'a', to.charAt(1) - '1' );

            if ( isMoveInvalid( pos1, pos2, isWhiteMove ) )
            {
                System.out.println( INVALID_MOVE );
                continue;
            }
            if ( chessBoard[pos1.getY()][pos1.getX()].isValidMove( pos1, pos2, chessBoard ) )
            {
                chessBoard[pos2.getY()][pos2.getX()] = chessBoard[pos1.getY()][pos1.getX()];
                chessBoard[pos1.getY()][pos1.getX()] = null;
            }

            printChessBoard();
            isWhiteMove = !isWhiteMove;
        }
    }


    private void printChessBoard()
    {
        for (int row = 7; row >= 0; row--)
        {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < 8; col++)
            {
                Piece currentPiece = chessBoard[row][col];
                if ( currentPiece == null )
                {
                    sb.append("--");
                }
                else {
                    sb.append( currentPiece.getPieceColor() );
                    sb.append( currentPiece.getPieceType() );
                }
                sb.append(" ");
            }
            sb.deleteCharAt( sb.length() - 1 );
            System.out.println( sb );
        }
    }


    private boolean isMoveInvalid( Coordinate from, Coordinate to, boolean isWhiteMove )
    {
        return !isValidPositions( from, to )
                || ( chessBoard[from.getY()][from.getX()] == null )
                || ( isWhiteMove && PieceColor.B.equals( chessBoard[from.getY()][from.getX()].getPieceColor() ) )
                || ( !isWhiteMove && PieceColor.W.equals( chessBoard[from.getY()][from.getX()].getPieceColor() ) )
                || ( chessBoard[to.getY()][to.getX()]!=null && isWhiteMove && PieceColor.W.equals( chessBoard[to.getY()][to.getX()].getPieceColor() ) )
                || ( chessBoard[to.getY()][to.getX()]!=null && !isWhiteMove && PieceColor.B.equals( chessBoard[to.getY()][to.getX()].getPieceColor() ) );
    }


    private boolean isValidPositions( Coordinate from, Coordinate to )
    {
        return isValidPosition( from.getX() )
                && isValidPosition(from.getY() )
                && isValidPosition(to.getX() )
                && isValidPosition(to.getY() )
                && !( from.getX() == to.getX() && from.getY() == to.getY() );
    }


    private boolean isValidPosition( int pos )
    {
        return pos < 8 && pos >= 0;
    }
}
