        public V getValue() {
if(this.last.hasPrevious()){
                throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
            }
            return parent.get(last);
        }