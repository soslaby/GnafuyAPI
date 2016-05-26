package tw.edu.nccu.soslab.gnafuy.api.utils;

/**
 * The descendant of Interaction offers abilities to:
 * 1. convert type T to string
 * 2. convert string to instance with type T
 * Created by jjchen on 2016/5/24.
 */
public abstract class Interaction<T> {
    protected final T value;
    protected final String valueAsString;

    /**
     * to specify where the element should go
     */
    protected String toQueue;

    public Interaction(T value) {
        this.value = value;
        this.valueAsString = getStringFromValue(value);
    }

    public Interaction(String valueAsString) {
        this.valueAsString = valueAsString;
        this.value = getValueFromString(valueAsString);
    }

    /**
     * Convert specific string to corresponding instance with type T
     *
     * @param from
     * @return instance of type T
     */
    abstract protected T getValueFromString(String from);

    /**
     * Convert instance with type T to specific string
     *
     * @param from
     * @return specific string
     */
    abstract protected String getStringFromValue(T from);

    public T getCopyOfValue() {
        return getValueFromString(valueAsString);
    }

    public String getValueAsString() {
        return valueAsString;
    }

    public String getToQueue() {
        return toQueue;
    }
}