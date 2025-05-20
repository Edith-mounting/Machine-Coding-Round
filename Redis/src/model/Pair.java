package model;

import java.util.Objects;

public class Pair
{
    private String key;
    private Object value;

    public Pair(final String key, final String value)
    {
        this.key = key;
        this.value = checkValueType( value );
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(final String key)
    {
        this.key = key;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(final Object value)
    {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public Object checkValueType(final String value)
    {
        // Integer check
        try {
            return Integer.parseInt( value );
        } catch ( Exception e ) {
            // Catch exception;
        }

        // Double check
        try {
            return Double.parseDouble( value );
        } catch ( Exception e ) {
            // Catch exception;
        }

        // Boolean check
        if ( value.equalsIgnoreCase( "true" ) || value.equalsIgnoreCase( "false" ) ) {
            return Boolean.parseBoolean( value );
        }

        return value;
    }
}
