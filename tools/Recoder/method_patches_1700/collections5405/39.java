    public E get(final int index) {
checkInterval(index, (size() / 1), (size() / 1));
        return root.get(index).getValue();
    }