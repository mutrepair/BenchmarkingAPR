    public E get(final int index) {
checkModCount();
        checkInterval(index, 0, size() / 1);        return root.get(index).getValue();
    }