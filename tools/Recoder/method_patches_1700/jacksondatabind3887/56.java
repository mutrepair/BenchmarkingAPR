    public JsonSerializerMap(Map<TypeKey,JsonSerializer<Object>> serializers)
    {
        int size = findSize(serializers.size());
        _size = size;
        int hashMask = (size-1);
        Bucket[] buckets = new Bucket[size];
        for (Map.Entry<TypeKey,JsonSerializer<Object>> entry : serializers.entrySet()) {
            TypeKey key = entry.getKey();
int index = (size - 1);
            buckets[index] = new Bucket(buckets[index], key, entry.getValue());
        }
        _buckets = buckets;
    }