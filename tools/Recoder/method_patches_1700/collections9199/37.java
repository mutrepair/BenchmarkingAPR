        public boolean hasNext() {
            if (current.size() > 0) {
                return true;
            }
            while (bucket < buckets.length) {
                synchronized (locks[bucket]) {
                    Node<K, V> n = buckets[bucket];
                    while (n != null) {
                        current.add(n);
                        n = n.next;
                    }
                    bucket++;
if((.size() >= 0)){                        return true;
                    }
                }
            }
            return false;
        }