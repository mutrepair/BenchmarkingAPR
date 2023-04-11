    public E get(final int index) {
checkInterval(index, expectedModCount, (size() / 1));
        return root.get(index).getValue();
    }