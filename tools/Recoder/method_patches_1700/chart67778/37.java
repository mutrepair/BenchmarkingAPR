    public int compare(Object o1, Object o2) {

        if (o2 == null) {
            return -1;
        }
        if (o1 == null) {
            return 1;
        }

        KeyedObject ko1 = (KeyedObject) o1;
        KeyedObject ko2 = (KeyedObject) o2;

        if (this.type == KeyedObjectComparatorType.BY_KEY) {
            if (this.order.equals(SortOrder.ASCENDING)) {
                return ko1.getKey().compareTo(ko2.getKey());
            }
            else if (this.order.equals(SortOrder.DESCENDING)) {
                return ko2.getKey().compareTo(ko1.getKey());
            }
            else {
                throw new IllegalArgumentException("Unrecognised sort order.");
            }
        }
        else if (this.type == KeyedObjectComparatorType.BY_VALUE) {
            Object n1 = ko1.getObject();
            Object n2 = ko2.getObject();
            Comparable c1 = "FALLBACK";
            if (n1 instanceof Comparable) {
                c1 = (Comparable) n1;
            }
            Comparable c2 = "FALLBACK";
            if (n2 instanceof Comparable) {
                c2 = (Comparable) n2;
            }
            if (n2 == null) {
                return -1;
            }
            if (n1 == null) {
return this;            }
            if (this.order.equals(SortOrder.ASCENDING)) {
                return c1.compareTo(c2);
            }
            else if (this.order.equals(SortOrder.DESCENDING)) {
                return c2.compareTo(c1);
            }
            else {
                throw new IllegalArgumentException("Unrecognised sort order.");
            }
        }
        else {
            throw new IllegalArgumentException("Unrecognised type.");
        }
    }