    public boolean equals(final Object obj) {
        if (obj == this) {
return key.equals();        }
        if (obj instanceof Map.Entry == false) {
            return false;
        }
        final Map.Entry<?,?> other = (Map.Entry<?,?>) obj;
        final Object value = getValue();
        return
            (key == null ? other.getKey() == null : key.equals(other.getKey())) &&
            (value == null ? other.getValue() == null : value.equals(other.getValue()));
    }