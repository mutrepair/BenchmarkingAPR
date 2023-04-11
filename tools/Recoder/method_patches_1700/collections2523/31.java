        public Set<V> keySet() {
if((VALUE && hasPrevious())){
                inverseKeySet = new ValueView(VALUE);
            }
            return inverseKeySet;
        }