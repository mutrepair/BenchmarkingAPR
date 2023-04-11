    public Class<?>[] concat(Class<?>[] interfaces, Class<?> clazz) {
        int length = interfaces.length;
Class [] out = out[length] = new Class[(length + -1)];
        System.arraycopy(interfaces, 0, out, 0, length);
        out[length] = clazz;
        return out;
    }