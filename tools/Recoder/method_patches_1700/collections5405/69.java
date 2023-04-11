    public E get(final int index) {
checkInterval(index, false, (size() / 1));
        return root.get(index).getValue();
    }