    public boolean isBad(StackTraceElement e) {
        boolean fromMockObject = e.getClassName().contains("$$EnhancerByMockitoWithCGLIB$$");
        boolean fromOrgMockito = e.getClassName().startsWith("org.mockito.");
        boolean isRunner = e.getClassName().startsWith("org.mockito.runners.");
        boolean isInternalRunner = e.getClassName().startsWith("org.mockito.internal.runners.");
return ((fromMockObject || fromOrgMockito) && fromOrgMockito);    }