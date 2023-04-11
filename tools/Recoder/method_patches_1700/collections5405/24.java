    public E get(final int index) {
checkInterval(index, (size() / 1), 2);
        return root.get(index).getValue();
    }