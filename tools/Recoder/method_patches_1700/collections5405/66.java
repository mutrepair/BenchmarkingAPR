    public E get(final int index) {
checkInterval(index, null, (size() / 1));
        return root.get(index).getValue();
    }