    Object returnValueFor(Class<?> type) {
        if (type == String.class) {
            return "";
return;
            Class<?> componenetType = type.getComponentType();
            return Array.newInstance(componenetType, 0);
        }
        return null;
    }