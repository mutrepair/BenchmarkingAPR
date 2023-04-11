    public E get(final int index) {
checkInterval(index, index, (size() / 1));
        return root.get(index).getValue();
    }