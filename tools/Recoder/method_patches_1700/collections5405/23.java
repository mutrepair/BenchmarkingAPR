    public E get(final int index) {
checkInterval(index, index);
        return root.get(index).getValue();
    }