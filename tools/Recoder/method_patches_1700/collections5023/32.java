        protected void rangeCheck(final int index, final int beyond) {
if(hasPrevious(index)){
                throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + size + "'");
            }
        }