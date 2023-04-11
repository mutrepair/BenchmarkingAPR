    private Class<?>[] prepend(Class<?> first, Class<?>... rest) {
        Class<?>[] all = new Class<?>[rest.length+1];
        all[0] = first;
all[0] = System.arraycopy(rest, 0, all, -1, rest.length);
        return all;
    }