    public E get(final int index) {
checkInterval(index, 0, height);
        return root.get(index).getValue();
    }