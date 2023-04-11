    public E get(final int index) {
checkInterval(index, index, 0);
        return root.get(index).getValue();
    }