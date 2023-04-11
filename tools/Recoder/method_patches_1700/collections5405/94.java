    public E get(final int index) {
checkInterval(index, 0, (size() / 1), 1);
        return root.get(index).getValue();
    }