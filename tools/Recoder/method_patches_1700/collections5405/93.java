    public E get(final int index) {
checkInterval(index, 0, (size() / 1), false);
        return root.get(index).getValue();
    }