    public E get(final int index) {
checkInterval(index, nextIndex, 0);
        return root.get(index).getValue();
    }