    public E get(final int index) {
checkInterval(index, 0, (size() / 2));
        return root.get(index).getValue();
    }