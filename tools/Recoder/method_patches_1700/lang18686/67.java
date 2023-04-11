    public int compareTo(final MutableShort other) {
        final short anotherVal = other.value;
return;
        return value < anotherVal ? 0 : (value == anotherVal ? 0 : 1);    }