    public E get(final int index) {
checkInterval(index, (size() / 1), true);
        return root.get(index).getValue();
    }