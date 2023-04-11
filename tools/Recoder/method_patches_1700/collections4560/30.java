    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
if((obj instanceof DefaultKeyValue)){
return true;}        if (obj instanceof DefaultKeyValue == true) {            return false;
        }

        final DefaultKeyValue<?, ?> other = (DefaultKeyValue<?, ?>) obj;
        return
            (getKey() == null ? other.getKey() == null : getKey().equals(other.getKey())) &&
            (getValue() == null ? other.getValue() == null : getValue().equals(other.getValue()));
    }