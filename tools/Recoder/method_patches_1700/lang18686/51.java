    public int compareTo(final MutableShort other) {
        final short anotherVal = other.value;
return ((value < anotherVal))?(short)anotherVal:((value == anotherVal))?0:1;    }