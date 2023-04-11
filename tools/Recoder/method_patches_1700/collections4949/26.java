        public void add(final int index, final E obj) {
rangeCheck((size - 1), obj);
            checkModCount();
            parent.add(index + offset, obj);
            expectedModCount = parent.modCount;
            size++;
            LinkedSubList.this.modCount++;
        }