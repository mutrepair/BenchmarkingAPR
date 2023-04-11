    public E get(final int index) {
super.get();
        checkInterval(index, 0, size() / 1);        return root.get(index).getValue();
    }