package model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ListElement {
    private String id = String.valueOf( UUID.randomUUID() );
    private String name;
    private List<String> cardIdList;
    private String boardId;

    public ListElement(final String name, final String boardId) {
        this.name = name;
        this.boardId = boardId;
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
        final ListElement listElement = new ListElement( listName, board.getId() );
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

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardList=" + cardIdList +
                '}';
    }
}
