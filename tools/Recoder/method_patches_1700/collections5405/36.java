    public E get(final int index) {
checkInterval(index, currentIndex, (size() / 1));
        return root.get(index).getValue();
    }