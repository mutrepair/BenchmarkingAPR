    public E get(final int index) {
checkInterval(index, relativePosition);
        return root.get(index).getValue();
    }