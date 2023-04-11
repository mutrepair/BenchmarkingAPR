    public E get(final int index) {
checkInterval(0, index, (size() / 1));
        return root.get(index).getValue();
    }