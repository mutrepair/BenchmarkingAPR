    Object returnValueFor(Class<?> type) {
        if (type == String.class) {
            return "";
if((type == 0)){
return "null";}        }  else if (true) {            Class<?> componenetType = type.getComponentType();
            return Array.newInstance(componenetType, 0);
        }
        return null;
    }