import model.*;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String, Board> boardMap = new HashMap<>();
        Map<String, ListElement> listElementMap = new HashMap<>();
        Map<String, Card> cardMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        while ( true ) {
            String commandType = sc.next();

            if ("exit".equals( commandType )) {
                return;
            }

            if( "SHOW".equals( commandType ) )
            {
                String result = doShowOperation( boardMap, listElementMap, cardMap, sc );
                System.out.println( result );
            }
            else if ( "BOARD".equals( commandType ) )
            {
                doBoardOperation( boardMap, listElementMap, cardMap, sc );
            }
            else if ( "LIST".equals( commandType ) )
            {
                doListOperation( boardMap, listElementMap, cardMap, sc );
            }
            else
            {
                doCardOperation( listElementMap, cardMap, sc );
            }
        }
    }


    public static void doCardOperation(final Map<String, ListElement> listElementMap,
                                       final Map<String, Card> cardMap,
                                       final Scanner sc) {
        String operation = sc.next();
        if ( "CREATE".equals( operation ) )
        {
            String listElementId = sc.next();
            String cardName = sc.next();
            Card.createCard( listElementMap, cardMap, listElementId, cardName );
        }
        else if ( "DELETE".equals( operation ) )
        {
            String cardId = sc.next();
            Card.removeCard( listElementMap, cardMap, cardId );
        }
        else
        {
            String cardId = operation;
            Card card = cardMap.get( cardId );
            operation = sc.next();
            if ( "UNASSIGN".equals( operation ) )
            {
                card.setUser( null );
                return;
            }
            String nextOperation = sc.next();
            Card.updateCard( listElementMap, operation, nextOperation, card, cardId );
        }
    }

    public static void doListOperation(final Map<String, Board> boardMap,
                                       final Map<String, ListElement> listElementMap,
                                       final Map<String, Card> cardMap,
                                       final Scanner sc) {
        final String operation = sc.next();
        if ( "CREATE".equals( operation ) )
        {
            String boardId = sc.next();
            String listName = sc.nextLine();
            ListElement.createListElement( boardMap, listElementMap, boardId, listName );
        }
        else if ( "DELETE".equals( operation ) )
        {
            String listElementId = sc.next();
            ListElement.removeListElement(boardMap, listElementMap, cardMap, listElementId);
        }
        else {
            String name = sc.next();
            String newName = sc.nextLine();
            listElementMap.get( operation ).setName( newName );
        }
    }

    public static String doShowOperation(final Map<String, Board> boardMap,
                                         final Map<String, ListElement> listElementMap,
                                         final Map<String, Card> cardMap,
                                         final Scanner sc)
    {
        if ( !sc.hasNext() )
        {
            if ( boardMap.isEmpty() )
            {
                return "";
            }
            final StringBuilder boardListStringBuilder = new StringBuilder( "[" );
            for (Map.Entry<String, Board> boardEntry : boardMap.entrySet()) {
                boardListStringBuilder.append( boardEntry.getValue().toString( boardMap, listElementMap, cardMap, boardEntry.getKey() ) );
                boardListStringBuilder.append( ", ");
            }
            boardListStringBuilder.deleteCharAt( boardListStringBuilder.length() - 1 );
            boardListStringBuilder.deleteCharAt( boardListStringBuilder.length() - 1 );
            boardListStringBuilder.append( "]" );
            return boardListStringBuilder.toString();
        }
        final String element = sc.next();
        final String id = sc.next();
        if ( "BOARD".equals( element ) )
        {
            final Board board = boardMap.get( id );
            if ( board==null )
            {
                return "";
            }
            return board.toString( boardMap, listElementMap, cardMap, id );
        }
        else if ( "LIST".equals( element ) )
        {
            final ListElement listElement = listElementMap.get( id );
            if ( listElement==null )
            {
                return "";
            }
            return listElement.toString( listElementMap, cardMap, id );
        }
        else
        {
            final Card card = cardMap.get( id );
            if ( card==null )
            {
                return "";
            }
            return card.toString();
        }
    }

    public static void doBoardOperation(final Map<String, Board> boardMap,
                                        final Map<String, ListElement> listElementMap,
                                        final Map<String, Card> cardMap,
                                        final Scanner sc)
    {
        final String operation = sc.next();
        if ( "CREATE".equals( operation ) )
        {
            String name = sc.next();
            Board.createBoard( boardMap, name );
        }
        else if ( "DELETE".equals( operation ) )
        {
            String id = sc.next();
            Board.removeBoard( boardMap, listElementMap, cardMap, id );
        }
        else {
            String id = operation;
            String nextOperation = sc.next();
            String updatedEntry = sc.next();
            Board.updateBoard( boardMap, id, nextOperation, updatedEntry );
        }
    }
}