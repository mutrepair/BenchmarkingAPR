    public E get(final int index) {
checkInterval(index, height, (size() / 1));
        return root.get(index).getValue();
    }