import model.Pair;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        final Redis redis = new Redis();
        Scanner scanner = new Scanner( System.in );
        while (true)
        {
            String operation = scanner.next();
            if ( "exit".equals( operation ) )
            {
                return;
            }
            else if ( "get".equals( operation ) )
            {
                String key = scanner.next();
                List<Pair> attributes = redis.get( key );
                printAttributeList( key, attributes );
            }
            else if ( "put".equals( operation ) )
            {
                String key = scanner.next();
                String line = scanner.nextLine();
                List<String> list = Arrays.asList( line.split(" ") );
                List<Pair> attributeList = new ArrayList<>();
                for(int i = 0; i < list.size(); i+=2)
                {
                    attributeList.add( new Pair( list.get( i ), list.get( i + 1 ) ) );
                }
                boolean isPutSuccessful = redis.put( key, attributeList );
                if ( !isPutSuccessful )
                {
                    System.out.println( "Data Type Error" );
                }
            }
            else if ( "delete".equals( operation ) )
            {
                String key = scanner.next();
                redis.delete( key );
            }
            else if ( "search".equals( operation ) )
            {
                String key = scanner.next();
                String value = scanner.next();
                List<String> keys = redis.search( key, value );
                printKeys( keys );
            }
            else
            {   //Keys
                List<String> keys = redis.keys();
                printKeys( keys );
            }
        }
    }

    public static void printKeys( final List<String> keys )
    {
        if (keys==null || keys.isEmpty())
        {
            return;
        }
        Collections.sort( keys );
        StringBuilder stringBuilder = new StringBuilder();
        keys.forEach(
                key -> {
                    stringBuilder.append( key );
                    stringBuilder.append( "," );
                }
        );
        stringBuilder.deleteCharAt( stringBuilder.length() - 1 );
        System.out.println( stringBuilder );
    }

    public static void printAttributeList( final String key, final List<Pair> attributes )
    {
        if (attributes==null || attributes.isEmpty())
        {
            System.out.println( "No entry found for " + key );
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        attributes.forEach(
                attribute -> {
                    stringBuilder.append( attribute.getKey() );
                    stringBuilder.append( ": " );
                    stringBuilder.append( attribute.getValue() );
                    stringBuilder.append( ", " );
                }
        );
        stringBuilder.deleteCharAt( stringBuilder.length() - 1 );
        stringBuilder.deleteCharAt( stringBuilder.length() - 1 );
        System.out.println( stringBuilder );
    }
}