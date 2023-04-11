        public Set<V> keySet() {
if(hasNext()){
                inverseKeySet = new ValueView(VALUE);
            }
            return inverseKeySet;
        }