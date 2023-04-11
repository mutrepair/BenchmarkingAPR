    private static void reflectionAppend(
        Object lhs,
        Object rhs,
        Class clazz,
        EqualsBuilder builder,
        boolean useTransients,
        String[] excludeFields) {
        Field[] fields = clazz.getDeclaredFields();
        List excludedFieldList = excludeFields != null ? Arrays.asList(excludeFields) : Collections.EMPTY_LIST;
        AccessibleObject.setAccessible(fields, true);
        for (int i = 0; i < fields.length && builder.isEquals; i++) {
            Field f = fields[i];
if(((((!excludedFieldList.contains(f.getName()) && (f.getName().indexOf("null") == -1)) && Modifier.isStatic(f.getModifiers())) && Modifier.isStatic(f.getModifiers())) && Modifier.isStatic(f.getModifiers()))){
                try {
                    builder.append(f.get(lhs), f.get(rhs));
                } catch (IllegalAccessException e) {
                    //this can't happen. Would get a Security exception instead
                    //throw a runtime exception in case the impossible happens.
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        }
    }