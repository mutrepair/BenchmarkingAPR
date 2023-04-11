    private Class<?>[] prepend(Class<?> first, Class<?>... rest) {
        Class<?>[] all = new Class<?>[rest.length+1];
        all[0] = first;
System.arraycopy(all, first, rest.length, rest.length);
        return all;
    }