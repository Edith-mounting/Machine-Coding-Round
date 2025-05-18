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
                doShowOperation( boardMap, sc );
            }
            else if ( "BOARD".equals( commandType ) )
            {
                doBoardOperation( boardMap, sc );
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
            }
            String nextOperation = sc.nextLine();
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

    public static void doShowOperation(final Map<String, Board> boardMap, final Scanner sc) {
        if ( !sc.hasNext() ) {
            List<Board> boardList = new ArrayList<>();
            for (Map.Entry<String, Board> entry : boardMap.entrySet()) {
                boardList.add( entry.getValue() );
            }
            System.out.println( boardList );
        }
        String element = sc.next();
        String id = sc.next();
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
            String id = sc.next();
            String nextOperation = sc.next();
            String updatedEntry = sc.next();
            Board.updateBoard( boardMap, id, nextOperation, updatedEntry );
        }
    }
}