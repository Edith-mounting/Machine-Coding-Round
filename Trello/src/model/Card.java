package model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Card {
    private String id = String.valueOf( UUID.randomUUID() );
    private String name;
    private String description;
    private User user;
    private String listElementId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getListElementId() {
        return listElementId;
    }

    public void setListElementId(String listElementId) {
        this.listElementId = listElementId;
    }

    public Card(final String name, final String listElementId) {
        this.name = name;
        this.listElementId = listElementId;
    }

    public static void createCard(final Map<String, ListElement> listElementMap,
                                  final Map<String, Card> cardMap,
                                  final String listElementId,
                                  final String cardName)
    {
        final ListElement listElement = listElementMap.get( listElementId );
        if (listElement == null)
        {
            System.out.println("List " + listElementId + " does not exist");
            return;
        }
        final Card card = new Card( cardName, listElementId );
        cardMap.put( card.getId(), card );
        listElement.getCardIdList().add( card.getId() );
    }

    public static void removeCard(final Map<String, ListElement> listElementMap,
                                  final Map<String, Card> cardMap,
                                  final String cardId)
    {
        final Card card = cardMap.get( cardId );
        if (card == null)
        {
            System.out.println("Card " + cardId + " does not exist");
            return;
        }
        listElementMap.get( card.getListElementId() ).getCardIdList().remove( cardId );
        cardMap.remove( cardId );
    }

    public static void updateCard(final Map<String, ListElement> listElementMap,
                                  final String operation,
                                  final String nextOperation,
                                  final Card card,
                                  final String cardId)
    {
        if ( "name".equals( operation ) )
        {
            card.setName( nextOperation );
        }
        else if( "description".equals( operation ) )
        {
            card.setDescription( nextOperation );
        }
        else if( "ASSIGN".equals( operation ) )
        {
            card.setUser( new User( nextOperation ) );
        }
        else if ( "MOVE".equals( operation ) )
        {
            // Move the card to another List.
            ListElement newListElement = listElementMap.get( nextOperation );
            if ( Objects.isNull( newListElement ) )
            {
                System.out.println("Second List with " + nextOperation + " doesn't exist");
            }
            ListElement listElement = listElementMap.get( card.getListElementId() );
            listElement.getCardIdList().remove( cardId );

            newListElement.getCardIdList().add( cardId );
            card.setListElementId( nextOperation );
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
