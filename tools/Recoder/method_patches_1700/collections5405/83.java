    public E get(final int index) {
checkInterval(index, currentIndex, 0);
        return root.get(index).getValue();
    }