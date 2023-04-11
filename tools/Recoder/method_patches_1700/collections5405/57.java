    public E get(final int index) {
checkInterval(index, 0, size);
        return root.get(index).getValue();
    }