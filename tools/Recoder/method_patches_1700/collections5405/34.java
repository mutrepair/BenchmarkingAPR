    public E get(final int index) {
checkInterval(index, relativePosition, (size() / 1));
        return root.get(index).getValue();
    }