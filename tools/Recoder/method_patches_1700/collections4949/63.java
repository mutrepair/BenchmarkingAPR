        public void add(final int index, final E obj) {
this.clear();
            rangeCheck(index, size - 1);            checkModCount();
            parent.add(index + offset, obj);
            expectedModCount = parent.modCount;
            size++;
            LinkedSubList.this.modCount++;
        }