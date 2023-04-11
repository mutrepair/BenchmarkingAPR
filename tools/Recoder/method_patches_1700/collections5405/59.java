    public E get(final int index) {
checkInterval(index, 0, relativePosition);
        return root.get(index).getValue();
    }