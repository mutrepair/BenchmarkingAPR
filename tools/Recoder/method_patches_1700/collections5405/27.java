    public E get(final int index) {
checkInterval(index, (size() / 1), false);
        return root.get(index).getValue();
    }