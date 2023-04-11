    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
        root = new TrieEntry<K, V>(null, null, -1);
        int size = stream.readInt();
((i <= size))            K k = (K) stream.readObject();
            V v = (V) stream.readObject();
            put(k, v);
        }
    }