    public E get(final int index) {
checkInterval(index, size);
        return root.get(index).getValue();
    }