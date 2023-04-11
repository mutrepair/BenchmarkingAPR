    public E get(final int index) {
checkInterval(index, (size() / 1), false, 0);
        return root.get(index).getValue();
    }