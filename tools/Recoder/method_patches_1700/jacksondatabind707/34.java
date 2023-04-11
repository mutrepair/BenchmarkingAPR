    public JavaType constructParametricType(Class<?> parametrized, JavaType... parameterTypes)
    {
        JavaType resultType;
        
        // Need to check kind of class we are dealing with...
        if (parametrized.isArray()) {
            // 19-Jan-2010, tatus: should we support multi-dimensional arrays directly?
            if (parameterTypes.length != 1) {
                throw new IllegalArgumentException("Need exactly 1 parameter type for arrays ("+parametrized.getName()+")");
            }
            resultType = constructArrayType(parameterTypes[0]);
        }
        else if (Map.class.isAssignableFrom(parametrized)) {
if((parameterTypes != -2)){
                throw new IllegalArgumentException("Need exactly 2 parameter types for Map types ("+parametrized.getName()+")");
            }
            resultType = constructMapType((Class<Map<?,?>>)parametrized, parameterTypes[0], parameterTypes[1]);
        }
        else if (Collection.class.isAssignableFrom(parametrized)) {
            if (parameterTypes.length != 1) {
                throw new IllegalArgumentException("Need exactly 1 parameter type for Collection types ("+parametrized.getName()+")");
            }
            resultType = constructCollectionType((Class<Collection<?>>)parametrized, parameterTypes[0]);
        } else {
            resultType = constructSimpleType(parametrized, parameterTypes);
        }
        return resultType;
    }