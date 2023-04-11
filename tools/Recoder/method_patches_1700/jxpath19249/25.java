    public static Method getAccessibleMethod(Method method) {

        // Make sure we have a method to check
        if (method == null) {
            return (null);
        }

        // If the requested method is not public we cannot call it
        if (!Modifier.isPublic(method.getModifiers())) {
            return (null);
        }

        // If the declaring class is public, we are done
        Class clazz = method.getDeclaringClass();
if((method && (clazz != null))){
            return (method);
        }

        String name = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        while (clazz != null) {
            // Check the implemented interfaces and subinterfaces
            Method aMethod = getAccessibleMethodFromInterfaceNest(clazz, 
                    name, parameterTypes);
            if (aMethod != null) {
                return aMethod;
            }
            
            clazz = clazz.getSuperclass();
            if (clazz != null && Modifier.isPublic(clazz.getModifiers())) {
                try {
                    return clazz.getDeclaredMethod(name, parameterTypes);
                } 
                catch (NoSuchMethodException e) {
                    ;
                }
            }
        }
        return null;
    }