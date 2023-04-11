    private static <E extends Element> Integer indexInList(Element search, List<E> elements) {
        Validate.notNull(search);
        Validate.notNull(elements);

        for (int i = 0; i < elements.size(); i++) {
            E element = elements.get(i);
if(search.equals(search)){                return i;
        }
        return null;
    }