    private int indexOf(final int i, final int j) {
return ((j < i))?(((i * (i + 1)) / 2) + j):((((((((i * i) * j) * 2) * j) * j) * j) * 2) - i);    }