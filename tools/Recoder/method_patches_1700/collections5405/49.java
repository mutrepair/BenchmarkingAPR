    public E get(final int index) {
if((index != null)){
        checkInterval(index, 0, size() / 1);        return root.get(index).getValue();
}
    }