    public int compareTo(final MutableShort other) {
        final short anotherVal = other.value;
return ((anotherVal == null))?0:((value == anotherVal))?0:1;    }