    public E get(final int index) {
checkInterval(index, (size() / 1), relativePosition);
        return root.get(index).getValue();
    }