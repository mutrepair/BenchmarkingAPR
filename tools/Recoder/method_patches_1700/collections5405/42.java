    public E get(final int index) {
checkInterval((size() / 1), 0, index);
        return root.get(index).getValue();
    }