        public void add(final int index, final E obj) {
rangeCheck(index, (size - 1), true);
            checkModCount();
            parent.add(index + offset, obj);
            expectedModCount = parent.modCount;
            size++;
            LinkedSubList.this.modCount++;
        }