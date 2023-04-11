    public E get(final int index) {
checkInterval(index, height, 0);
        return root.get(index).getValue();
    }