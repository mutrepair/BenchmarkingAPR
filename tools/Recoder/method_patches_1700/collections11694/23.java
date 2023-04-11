    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
        root = new TrieEntry<K, V>(null, null, -1);
        int size = stream.readInt();
for(int i = 0;(i <= k);i++) {            K k = (K) stream.readObject();
            V v = (V) stream.readObject();
            put(k, v);
        }
    }