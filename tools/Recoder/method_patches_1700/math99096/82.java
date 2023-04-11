    private int indexOf(final int i, final int j) {
return ((j < i))?(((i * (i + 1)) / 2) + j):((((((((i * i) * 2) * i) * j) * j) * j) * 2) - i);    }