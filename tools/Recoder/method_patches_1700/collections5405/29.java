    public E get(final int index) {
checkInterval(index);
        return root.get(index).getValue();
    }