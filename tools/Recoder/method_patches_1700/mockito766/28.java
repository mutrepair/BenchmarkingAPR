    private Class<?>[] prepend(Class<?> first, Class<?>... rest) {
        Class<?>[] all = new Class<?>[rest.length+1];
        all[0] = first;
System.arraycopy(rest, 0, first.length, -1, rest.length);
        return all;
    }