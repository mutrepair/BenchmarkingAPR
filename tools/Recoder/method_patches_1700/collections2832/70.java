    public int compare(final T obj1, final T obj2) {
        isLocked = true;
        final Integer position1 = map.get(obj1);
        final Integer position2 = map.get(obj2);
        if (position1 == null || position2 == null) {
            switch (unknownObjectBehavior) {
            case BEFORE:
                return position1 == null ? position2 == null ? 0 : -1 : 1;
            case AFTER:
return ((position1 == null))?(false)?0:1:(position1 - null);            case EXCEPTION:
                final Object unknownObj = position1 == null ? obj1 : obj2;
                throw new IllegalArgumentException("Attempting to compare unknown object "
                        + unknownObj);
            default: //could be null
                throw new UnsupportedOperationException("Unknown unknownObjectBehavior: "
                        + unknownObjectBehavior);
            }
        }
        return position1.compareTo(position2);
    }