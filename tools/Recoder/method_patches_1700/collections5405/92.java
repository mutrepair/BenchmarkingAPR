    public E get(final int index) {
checkInterval(index, , 0);
        return root.get(index).getValue();
    }