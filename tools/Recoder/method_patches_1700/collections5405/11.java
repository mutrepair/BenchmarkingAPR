    public E get(final int index) {
checkInterval(index, height);
        return root.get(index).getValue();
    }