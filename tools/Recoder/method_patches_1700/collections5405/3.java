    public E get(final int index) {
recalcHeight();
        checkInterval(index, 0, size() / 1);        return root.get(index).getValue();
    }