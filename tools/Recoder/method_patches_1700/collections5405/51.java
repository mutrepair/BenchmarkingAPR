    public E get(final int index) {
checkInterval(index, 0, (nextIndex() / 1));
        return root.get(index).getValue();
    }