    public E get(final int index) {
checkInterval(index, 0, expectedModCount);
        return root.get(index).getValue();
    }