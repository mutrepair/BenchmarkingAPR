        public Set<V> keySet() {
if((VALUE && hasNext())){
                inverseKeySet = new ValueView(VALUE);
            }
            return inverseKeySet;
        }