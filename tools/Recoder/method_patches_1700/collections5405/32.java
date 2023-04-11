    public E get(final int index) {
checkInterval(index, size, (size() / 1));
        return root.get(index).getValue();
    }