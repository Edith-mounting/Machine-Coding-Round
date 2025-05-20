import model.CountAndDataType;
import model.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Redis
{
    private final Map<String, List<Pair>> dataStore;
    private final Map<String, CountAndDataType> attributeKeyCount;

    public Redis()
    {
        this.dataStore = new HashMap<>();
        this.attributeKeyCount = new HashMap<>();
    }

    public List<Pair> get(final String key)
    {
        return dataStore.get( key );
    }

    public List<String> search(final String attributeKey, final String attributeValue)
    {
        Pair attributePair = new Pair( attributeKey, attributeValue );
        List<String> keys = new ArrayList<>();
        for ( Map.Entry<String, List<Pair>> entry: dataStore.entrySet() )
        {
            entry.getValue().forEach(
                    pair -> {
                        if ( pair.equals( attributePair ) )
                        {
                            keys.add( entry.getKey() );
                        }
                    }
            );
        }
        return keys;
    }

    public boolean put(final String key, final List<Pair> listOfAttributes)
    {
        // Check DataType first.
        for ( Pair attribute: listOfAttributes )
        {
            CountAndDataType countAndDataType = attributeKeyCount.get( attribute.getKey() );
            if ( countAndDataType!=null && countAndDataType.getType().getClass()!=attribute.getValue().getClass() )
            {
                return false;
            }
        }

        // Delete existing attributes.
        delete( key );

        // Add the new attribute keys in attributeKeyCount map.
        for ( Pair attribute: listOfAttributes )
        {
            // Check if the dataType already exist or not.
            CountAndDataType countAndDataType = attributeKeyCount.get( attribute.getKey() );
            if ( countAndDataType == null )
            {
                countAndDataType = new CountAndDataType( 0, attribute.getValue() );
            }
            countAndDataType.setCount( countAndDataType.getCount() + 1 );
            attributeKeyCount.put( attribute.getKey(), countAndDataType );
        }
        dataStore.put( key, listOfAttributes );
        return true;
    }

    public void delete(final String key)
    {
        List<Pair> attributes = dataStore.get( key );
        if (attributes==null || attributes.isEmpty())
        {
            return;
        }

        // Not empty
        attributes.forEach(
                attribute -> {
                    CountAndDataType countAndDataType = attributeKeyCount.get( attribute.getKey() );
                    if ( countAndDataType.getCount() == 1)
                    {
                        attributeKeyCount.remove( attribute.getKey() );
                    }
                    else {
                        countAndDataType.setCount( countAndDataType.getCount() - 1 );
                    }
                }
        );
        dataStore.remove( key );
    }

    public List<String> keys()
    {
        List<String> keyList = new ArrayList<>();
        for (Map.Entry<String, List<Pair>> entry: dataStore.entrySet())
        {
            keyList.add( entry.getKey() );
        }
        return keyList;
    }
}
