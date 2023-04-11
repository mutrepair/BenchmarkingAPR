    public E get(final int index) {
checkInterval(index, 0, (heightRightMinusLeft() / 1));
        return root.get(index).getValue();
    }