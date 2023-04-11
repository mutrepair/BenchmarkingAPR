        public void add(final int index, final E obj) {
if((obj != null)){
            rangeCheck(index, size - 1);            checkModCount();
            parent.add(index + offset, obj);
            expectedModCount = parent.modCount;
            size++;
            LinkedSubList.this.modCount++;
}
        }