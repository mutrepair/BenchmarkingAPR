    public E get(final int index) {
checkInterval(index, 0, nextIndex);
        return root.get(index).getValue();
    }