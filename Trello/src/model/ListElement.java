package model;

import java.util.*;

public class ListElement {
    private String id = String.valueOf( UUID.randomUUID() );
    private String name;
    private List<String> cardIdList;
    private String boardId;

    public ListElement(final String name, final String boardId) {
        this.name = name;
        this.boardId = boardId;
        this.cardIdList = new ArrayList<>();
    }

    public String getBoardId() {
        return boardId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCardIdList() {
        return cardIdList;
    }

    public static void createListElement(final Map<String, Board> boardMap,
                                         final Map<String, ListElement> listElementMap,
                                         final String boardId,
                                         final String listName)
    {
        final Board board = boardMap.get( boardId );
        if (board == null) {
            System.out.println("Board " + boardId + " does not exist");
            return;
        }
        final ListElement listElement = new ListElement( listName, boardId );
        listElementMap.put( listElement.getId(), listElement );
        board.getListIds().add( listElement.getId() );
    }

    public static void removeListElement(final Map<String, Board> boardMap,
                                         final Map<String, ListElement> listElementMap,
                                         final Map<String, Card> cardMap,
                                         final String listElementId)
    {
        final ListElement listElement = listElementMap.get( listElementId );
        if (listElement == null)
        {
            System.out.println("List " + listElementId + " does not exist");
            return;
        }

        // Remove all cards related to listElement.
        listElement.getCardIdList().forEach( cardMap::remove ) ;

        // Remove listElementId from list of listElementId of board.
        final String boardId = listElement.getBoardId();
        Board board = boardMap.get( boardId );
        if ( Objects.nonNull( board.getListIds() ) ) {
            board.getListIds().remove( listElementId );
        }

        // Remove listElement.
        listElementMap.remove( listElementId );
    }

    public String fetchAndCreateCardList(final Map<String, ListElement> listElementMap,
                                         final Map<String, Card> cardMap,
                                         final String listElementId)
    {
        if ( listElementMap.get( listElementId ).getCardIdList() == null || listElementMap.get( listElementId ).getCardIdList().isEmpty() )
        {
            return "";
        }
        StringBuilder cardListStringBuilder = new StringBuilder( "[" );
        listElementMap.get( listElementId ).getCardIdList().forEach(
                cardId -> {
                    cardListStringBuilder.append( cardMap.get( cardId ).toString() );
                    cardListStringBuilder.append(", ");
                }
        );
        cardListStringBuilder.deleteCharAt( cardListStringBuilder.length() - 1 );
        cardListStringBuilder.deleteCharAt( cardListStringBuilder.length() - 1 );
        cardListStringBuilder.append( "]" );
        return cardListStringBuilder.toString();
    }


    public String toString(final Map<String, ListElement> listElementMap,
                           final Map<String, Card> cardMap,
                           final String listElementId)
    {
        return "{" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", cards: " + fetchAndCreateCardList(listElementMap, cardMap, listElementId) +
                '}';
    }
}
