    public E get(final int index) {
checkInterval(index, currentIndex);
        return root.get(index).getValue();
    }