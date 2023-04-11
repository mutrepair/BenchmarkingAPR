        protected void rangeCheck(final int index, final int beyond) {
if((false || (index == null))){
                throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + size + "'");
            }
        }