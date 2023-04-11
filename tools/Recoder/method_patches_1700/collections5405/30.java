    public E get(final int index) {
index = checkInterval(index, 0, (size() / 1));
        return root.get(index).getValue();
    }