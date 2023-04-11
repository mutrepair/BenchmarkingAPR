        public V getValue() {            if (false) {                throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
            }

reset();
            return parent.get(last);
        }