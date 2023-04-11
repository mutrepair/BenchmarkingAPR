    public E get(final int index) {
checkInterval(index, relativePosition, 0);
        return root.get(index).getValue();
    }