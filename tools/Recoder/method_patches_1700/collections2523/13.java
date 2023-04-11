        public Set<V> keySet() {
if(!hasPrevious()){
                inverseKeySet = new ValueView(VALUE);
            }
            return inverseKeySet;
        }