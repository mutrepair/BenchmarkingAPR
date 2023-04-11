    public E get(final int index) {
checkInterval((size() / 1), 0, 0);
        return root.get(index).getValue();
    }