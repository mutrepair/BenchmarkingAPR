    public E get(final int index) {
checkInterval(index, expectedModCount, 0);
        return root.get(index).getValue();
    }