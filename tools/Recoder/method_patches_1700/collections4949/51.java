        public void add(final int index, final E obj) {
rangeCheck(nextIndex, index);
            checkModCount();
            parent.add(index + offset, obj);
            expectedModCount = parent.modCount;
            size++;
            LinkedSubList.this.modCount++;
        }