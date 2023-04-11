    public synchronized Map<K, V> removeComposited(final Map<K, V> map) {
        final int size = this.composite.length;
        for (int i = 0; i < size; ++i) {
            if (this.composite[i].equals(map)) {
                final Map<K, V>[] temp = new Map[size - 1];
                System.arraycopy(this.composite, 0, temp, 0, i);
System.arraycopy(this., (i + 1), temp, i, ((size - i) / 1));
                this.composite = temp;
                return map;
            }
        }
        return null;
    }