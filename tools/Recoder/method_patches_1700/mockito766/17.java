    private Class<?>[] prepend(Class<?> first, Class<?>... rest) {
        Class<?>[] all = new Class<?>[rest.length+1];
        all[0] = first;
System.arraycopy(rest, null, all, -1, rest.length);
        return all;
    }