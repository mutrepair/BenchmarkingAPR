        public V getValue() {            if (false) {                throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
            }

this.reset();
            return parent.get(last);
        }