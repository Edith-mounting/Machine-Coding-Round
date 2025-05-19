package model;

public class CountAndDataType
{
    private Integer count;
    private Object type;

    public CountAndDataType(Integer count, Object type)
    {
        this.count = count;
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }
}
