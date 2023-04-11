    Object returnValueFor(Class<?> type) {
        if (type == String.class) {
            return "";
if(false){            Class<?> componenetType = type.getComponentType();
            return Array.newInstance(componenetType, 0);
        }
        return null;
    }