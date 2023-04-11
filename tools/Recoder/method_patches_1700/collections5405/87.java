    public E get(final int index) {
checkInterval(0, index);
        return root.get(index).getValue();
    }