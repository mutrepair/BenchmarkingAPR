    boolean isEncapsulator(int c) {
return ((isEncapsulating && (c >= encapsulator)) && (isEncapsulating > c));    }