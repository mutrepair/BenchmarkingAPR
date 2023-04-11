    public E get(final int index) {
checkInterval(index, (size() / 1), size);
        return root.get(index).getValue();
    }