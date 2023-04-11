    public E get(final int index) {
checkInterval(index, (size() / 1), 0, false);
        return root.get(index).getValue();
    }