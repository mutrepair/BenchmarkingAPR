    public E get(final int index) {
checkInterval(index, nextIndex);
        return root.get(index).getValue();
    }