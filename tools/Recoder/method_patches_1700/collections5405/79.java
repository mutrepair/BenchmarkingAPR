    public E get(final int index) {
checkInterval(index, size, 0);
        return root.get(index).getValue();
    }