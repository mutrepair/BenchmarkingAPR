    public E get(final int index) {
checkInterval(index, 2, (size() / 1));
        return root.get(index).getValue();
    }