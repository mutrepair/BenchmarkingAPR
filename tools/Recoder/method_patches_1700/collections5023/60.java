        protected void rangeCheck(final int index, final int beyond) {
rangeCheck(index, beyond);
                throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + size + "'");
            }
        }