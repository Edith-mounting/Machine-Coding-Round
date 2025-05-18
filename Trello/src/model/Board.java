package model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Board {
    private String id = String.valueOf( UUID.randomUUID() );
    private String name;
    private Privacy privacy = Privacy.PUBLIC;
    private java.lang.String url;
    private List<User> members;
    private List<String> listIds;

    public Board(final String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<String> getListIds() {
        return listIds;
    }

    public static void createBoard(final Map<String, Board> boardMap,
                                   final String boardName)
    {
        Board newBoard = new Board( boardName );
        boardMap.put( newBoard.getId(), newBoard );
        System.out.println("Created board: " + newBoard.getId());
    }

    public static void removeBoard(final Map<String, Board> boardMap,
                                   final Map<String, ListElement> listElementMap,
                                   final Map<String, Card> cardMap,
                                   final String boardId)
    {
        final Board board = boardMap.get( boardId );
        if (board == null) {
            System.out.println("Board " + boardId + " does not exist");
            return;
        }

        if ( Objects.nonNull( board.getListIds() ) )
        {
            for ( final String listElementId : board.getListIds() )
            {
                ListElement listElement = listElementMap.get( listElementId );

                if ( listElement != null && Objects.nonNull( listElement.getCardIdList() ) )
                {
                    // Remove all cards related to these listElements.
                    listElement.getCardIdList().forEach( cardMap::remove );
                }

                // Remove the list element itself from the map
                listElementMap.remove( listElementId );
            }
            board.getListIds().clear();
        }

        boardMap.remove( boardId );
    }

    public static void updateBoard(final Map<String, Board> boardMap,
                                   final String boardId,
                                   final String operation,
                                   final String updatedEntry)
    {
        final Board board = boardMap.get( boardId );
        if (board == null)
        {
            System.out.println("Board " + boardId + " does not exist");
            return;
        }

        switch ( operation )
        {
            case "name" -> board.setName( updatedEntry );
            case "privacy" -> board.setPrivacy( Privacy.valueOf( updatedEntry ) );
            case "ADD_MEMBER" -> board.getMembers().add( new User( updatedEntry ) );
            case null, default -> {
                final List<User> userList = board.getMembers();
                User currentUser = null;
                for ( User user : userList ) {
                    if ( user.getName().equals( updatedEntry ) ) {
                        currentUser = user;
                    }
                }
                board.getMembers().remove( currentUser );
            }
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", privacy=" + privacy +
                ", url='" + url + '\'' +
                ", members=" + members +
                ", lists=" + listIds +
                '}';
    }
}
