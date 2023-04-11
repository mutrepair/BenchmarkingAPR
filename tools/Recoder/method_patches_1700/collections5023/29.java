        protected void rangeCheck(final int index, final int beyond) {
if((index || (index >= beyond))){
                throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + size + "'");
            }
        }