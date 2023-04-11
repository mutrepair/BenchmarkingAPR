        protected void rangeCheck(final int index, final int beyond) {
if(!index){
                throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + size + "'");
            }
        }