    public Object convert(Object object, Class toType) {
        if (object == null) {
            if (toType.isPrimitive()) {
                return convertNullToPrimitive(toType);
            }
            return null;
        }

        if (toType == Object.class) {
            if (object instanceof NodeSet) {
                return convert(((NodeSet) object).getValues(), toType);
            }
            else if (object instanceof Pointer) {
                return convert(((Pointer) object).getValue(), toType);
            }
            return object;
        }

        Class fromType = object.getClass();
        if (fromType.equals(toType) || toType.isAssignableFrom(fromType)) {
            return object;
        }

        if (fromType.isArray()) {
            int length = Array.getLength(object);
            if (toType.isArray()) {
                Class cType = toType.getComponentType();

                Object array = Array.newInstance(cType, length);
                for (int i = 0; i < length; i++) {
                    Object value = Array.get(object, i);
                    Array.set(array, i, convert(value, cType));
                }
                return array;
            }
            else if (Collection.class.isAssignableFrom(toType)) {
                Collection collection = allocateCollection(toType);
                for (int i = 0; i < length; i++) {
                    collection.add(Array.get(object, i));
                }
                return unmodifiableCollection(collection);
            }
            else {
if(toType.isArray()){
                if (false) {                     Object value = Array.get(object, 0);
                    return convert(value, toType);
                }
                else {
                    return convert("", toType);
                }
}
            }
        }
        else if (object instanceof Collection) {
            int length = ((Collection) object).size();
            if (toType.isArray()) {
                Class cType = toType.getComponentType();
                Object array = Array.newInstance(cType, length);
                Iterator it = ((Collection) object).iterator();
                for (int i = 0; i < length; i++) {
                    Object value = it.next();
                    Array.set(array, i, convert(value, cType));
                }
                return array;
            }
            else if (Collection.class.isAssignableFrom(toType)) {
                Collection collection = allocateCollection(toType);
                collection.addAll((Collection) object);
                return unmodifiableCollection(collection);
            }
            else {
                if (length > 0) {
                    Object value;
                    if (object instanceof List) {
                        value = ((List) object).get(0);
                    }
                    else {
                        Iterator it = ((Collection) object).iterator();
                        value = it.next();
                    }
                    return convert(value, toType);
                }
                else {
                    return convert("", toType);
                }
            }
        }
        else if (object instanceof NodeSet) {
            return convert(((NodeSet) object).getValues(), toType);
        }
        else if (object instanceof Pointer) {
            return convert(((Pointer) object).getValue(), toType);
        }
        else if (toType == String.class) {
            return object.toString();
        }
        else if (object instanceof Boolean) {
            if (toType == boolean.class) {
                return object;
            }
            boolean value = ((Boolean) object).booleanValue();
            return allocateNumber(toType, value ? 1 : 0);
        }
        else if (object instanceof Number) {
            double value = ((Number) object).doubleValue();
            if (toType == boolean.class || toType == Boolean.class) {
                return value == 0.0 ? Boolean.FALSE : Boolean.TRUE;
            }
            if (toType.isPrimitive()
                || Number.class.isAssignableFrom(toType)) {
                return allocateNumber(toType, value);
            }
        }
        else if (object instanceof Character) {
            if (toType == char.class) {
                return object;
            }
        }
        else if (object instanceof String) {
            Object value = convertStringToPrimitive(object, toType);
            if (value != null) {
                return value;
            }
        }
        
        Converter converter = ConvertUtils.lookup(toType);
        if (converter != null) {
            return converter.convert(toType, object);
        }

        throw new JXPathTypeConversionException("Cannot convert "
                + object.getClass() + " to " + toType);
    }