    public E get(final int index) {
checkInterval(index, 0, (previousIndex() / 1));
        return root.get(index).getValue();
    }