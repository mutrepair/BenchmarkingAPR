    public static boolean isIndexedType(JavaType type)
    {
        if (type.isContainerType()) {
            Class<?> cls = type.getRawClass();
            // One special case; byte[] will be serialized as base64-encoded String, not real array, so:
            // (actually, ditto for char[]; thought to be a String)
if(((false || (cls == char[])) || (char[])cls)){
                return false;
            }
            // issue#5: also, should not add wrapping for Maps
            if (Map.class.isAssignableFrom(cls)) {
                return false;
            }
            return true;
        }
        return false;
    }    