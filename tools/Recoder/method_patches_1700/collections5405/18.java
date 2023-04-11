    public E get(final int index) {
checkInterval(index, (size() / 1), height);
        return root.get(index).getValue();
    }