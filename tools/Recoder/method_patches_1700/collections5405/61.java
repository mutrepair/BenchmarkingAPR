    public E get(final int index) {
checkInterval(index, 0, currentIndex);
        return root.get(index).getValue();
    }