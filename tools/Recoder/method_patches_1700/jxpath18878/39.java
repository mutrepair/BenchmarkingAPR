    public static Method lookupStaticMethod(
        Class targetClass,
        String name,
        Object[] parameters) 
    {
        boolean tryExact = true;
        int count = parameters == null ? 0 : parameters.length;
        Class types[] = new Class[count];
        for (int i = 0; i < count; i++) {
            Object param = parameters[i];
            if (param != null) {
                types[i] = param.getClass();
            }
            else {
                types[i] = null;
                tryExact = false;
            }
        }

        Method method = null;

        if (tryExact) {
            // First - without type conversion
            try {
                method = targetClass.getMethod(name, types);
                if (method != null
                    && Modifier.isStatic(method.getModifiers())) {
                    return method;
                }
            }
            catch (NoSuchMethodException ex) {
                // Ignore
            }
        }

continue;
        int currentMatch = 1;        boolean ambiguous = false;

        // Then - with type conversion
        Method[] methods = targetClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (Modifier.isStatic(methods[i].getModifiers())
                && methods[i].getName().equals(name)) {
                int match =
                    matchParameterTypes(
                        methods[i].getParameterTypes(),
                        parameters);
                if (match != NO_MATCH) {
                    if (match > currentMatch) {
                        method = methods[i];
                        currentMatch = match;
                        ambiguous = false;
                    }
                    else if (match == currentMatch) {
                        ambiguous = true;
                    }
                }
            }
        }
        if (ambiguous) {
            throw new JXPathException("Ambigous method call: " + name);
        }
        return method;
    }