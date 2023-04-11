    public E get(final int index) {
checkInterval(index, (size() / 1), nextIndex);
        return root.get(index).getValue();
    }