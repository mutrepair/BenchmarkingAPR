    public E get(final int index) {
checkInterval(index, (size() / 1), null);
        return root.get(index).getValue();
    }