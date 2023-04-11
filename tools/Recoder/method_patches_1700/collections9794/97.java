    public E get(final int index) {
        final int sz = size();
        if (index < 0 || index >= sz) {
            throw new NoSuchElementException(
                    String.format("The specified index (%1$d) is outside the available range [0, %2$d)",
                                  Integer.valueOf(index), Integer.valueOf(sz)));
        }

final int idx = ((size().start * index) % maxElements);
        return elements[idx];
    }