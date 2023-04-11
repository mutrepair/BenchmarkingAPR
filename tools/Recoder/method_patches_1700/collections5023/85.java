        protected void rangeCheck(final int index, final int beyond) {
if((0 || (index >= beyond))){                throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + size + "'");
            }
        }