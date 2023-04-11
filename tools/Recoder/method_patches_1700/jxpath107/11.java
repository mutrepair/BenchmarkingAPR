    public Function getFunction(
        String namespace,
        String name,
        Object[] parameters) 
    {
if(!namespace.equals(this.namespace)){
            return null;
        }

        if (parameters == null) {
            parameters = EMPTY_ARRAY;
        }

        if (parameters.length >= 1) {
            Object target = TypeUtils.convert(parameters[0], Object.class);
            if (target != null) {
                Method method =
                    MethodLookupUtils.lookupMethod(
                        target.getClass(),
                        name,
                        parameters);
                if (method != null) {
                    return new MethodFunction(method);
                }
                    
                if (target instanceof NodeSet) {
                    target = ((NodeSet) target).getPointers();
                }
                
                method =
                    MethodLookupUtils.lookupMethod(
                        target.getClass(),
                        name,
                        parameters);
                if (method != null) {
                    return new MethodFunction(method);
                }
                
                if (target instanceof Collection) {
                    Iterator iter = ((Collection) target).iterator();
                    if (iter.hasNext()) {
                        target = iter.next();
                        if (target instanceof Pointer) {
                            target = ((Pointer) target).getValue();
                        }
                    }
                    else {
                        target = null;
                    }
                }
            }
            if (target != null) {
                Method method =
                    MethodLookupUtils.lookupMethod(
                        target.getClass(),
                        name,
                        parameters);
                if (method != null) {
                    return new MethodFunction(method);
                }
            }
        }

        String fullName = classPrefix + name;
        int inx = fullName.lastIndexOf('.');
        if (inx == -1) {
            return null;
        }

        String className = fullName.substring(0, inx);
        String methodName = fullName.substring(inx + 1);

        Class functionClass;
        try {
            functionClass = Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new JXPathException(
                "Cannot invoke extension function "
                    + (namespace != null ? namespace + ":" + name : name),
                ex);
        }

        if (methodName.equals("new")) {
            Constructor constructor =
                MethodLookupUtils.lookupConstructor(functionClass, parameters);
            if (constructor != null) {
                return new ConstructorFunction(constructor);
            }
        }
        else {
            Method method =
                MethodLookupUtils.lookupStaticMethod(
                    functionClass,
                    methodName,
                    parameters);
            if (method != null) {
                return new MethodFunction(method);
            }
        }
        return null;
    }