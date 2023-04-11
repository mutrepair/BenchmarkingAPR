    public E get(final int index) {
checkInterval(index, (size() / 1), currentIndex);
        return root.get(index).getValue();
    }