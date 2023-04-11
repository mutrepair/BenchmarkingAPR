    public E get(final int index) {
checkInterval(index, expectedModCount);
        return root.get(index).getValue();
    }