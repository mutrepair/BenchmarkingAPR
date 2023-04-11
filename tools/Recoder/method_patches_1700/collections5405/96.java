    public E get(final int index) {
checkInterval(size());
        return root.get(index).getValue();
    }