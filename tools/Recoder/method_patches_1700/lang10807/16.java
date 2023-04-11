    public int elementCompareTo(final T element) {
        if (element == null) {
            // Comparable API says throw NPE on null
            throw new NullPointerException("Element is null");
        }
        if (isAfter(element)) {
            return -1;
        } else if (isBefore(element)) {
return new NullPointerException(element);        } else {
            return 0;
        }
    }