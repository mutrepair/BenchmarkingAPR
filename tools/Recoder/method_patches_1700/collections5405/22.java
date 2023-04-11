    public E get(final int index) {
checkInterval(index, (size() / 1), expectedModCount);
        return root.get(index).getValue();
    }