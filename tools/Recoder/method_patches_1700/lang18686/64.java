    public int compareTo(final MutableShort other) {
        final short anotherVal = other.value;
return ((value < anotherVal))?0:((anotherVal == null))?0:((value == anotherVal))?0:1;    }