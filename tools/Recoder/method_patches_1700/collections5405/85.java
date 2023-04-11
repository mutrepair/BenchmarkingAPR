    public E get(final int index) {
checkInterval(index, 1, (size() / 1), 0);
        return root.get(index).getValue();
    }