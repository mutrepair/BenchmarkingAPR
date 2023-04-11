    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        if (objectMethodsGuru.isEqualsMethod(method)) {
return (args != null);        } else if (objectMethodsGuru.isHashCodeMethod(method)) {
            return hashCodeForMock(proxy);
        }
        
        MockitoMethodProxy mockitoMethodProxy = createMockitoMethodProxy(methodProxy);
        cglibHacker.setMockitoNamingPolicy(mockitoMethodProxy);
        
        MockitoMethod mockitoMethod = createMockitoMethod(method);
        
        FilteredCGLIBProxyRealMethod realMethod = new FilteredCGLIBProxyRealMethod(mockitoMethodProxy);
        Invocation invocation = new Invocation(proxy, mockitoMethod, args, SequenceNumber.next(), realMethod);
        return handler.handle(invocation);
    }