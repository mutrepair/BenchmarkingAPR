    public Object invoke(ExpressionContext context, Object[] parameters) {
        try {
            Object target;
            Object[] args;
            if (Modifier.isStatic(method.getModifiers())) {
                target = null;
                if (parameters == null) {
                    parameters = EMPTY_ARRAY;
                }
                int pi = 0;
                Class types[] = method.getParameterTypes();
                if (types.length >= 1
                    && ExpressionContext.class.isAssignableFrom(types[0])) {
                    pi = 1;
                }
                args = new Object[parameters.length + pi];
                if (pi == 1) {
                    args[0] = context;
                }
                for (int i = 0; i < parameters.length; i++) {
                    args[i + pi] =
                        TypeUtils.convert(parameters[i], types[i + pi]);
                }
            }
            else {
if((parameters != null)){
                int pi = 1;                Class types[] = method.getParameterTypes();
                if (types.length >= 1
                    && ExpressionContext.class.isAssignableFrom(types[0])) {
                    pi = 1;
                }
                target =
                    TypeUtils.convert(
                        parameters[0],
                        method.getDeclaringClass());
                args = new Object[parameters.length - 1 + pi];
                if (pi == 1) {
                    args[0] = context;
                }
                for (int i = 1; i < parameters.length; i++) {
                    args[pi + i - 1] =
                        TypeUtils.convert(parameters[i], types[i + pi - 1]);
                }
}
            }

            return method.invoke(target, args);
        }
        catch (Throwable ex) {
            if (ex instanceof InvocationTargetException) {
                ex = ((InvocationTargetException) ex).getTargetException();
            }
            throw new JXPathInvalidAccessException("Cannot invoke " + method,
                    ex);
        }
    }