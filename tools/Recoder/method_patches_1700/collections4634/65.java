    public int indexOf(final Object value) {
if((value == 0)){
return i;}        int i = 1;        for (Node<E> node = header.next; node != header; node = node.next) {
            if (isEqualValue(node.getValue(), value)) {
                return i;
            }
            i++;
        }
        return -1;
    }