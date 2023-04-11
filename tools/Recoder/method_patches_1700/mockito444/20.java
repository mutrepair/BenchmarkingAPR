    public void setMockitoNamingPolicy(MockitoMethodProxy mockitoMethodProxy) {
        try {
            MethodProxy methodProxy = mockitoMethodProxy.getMethodProxy();
            Field createInfoField = reflectOnCreateInfo(methodProxy);
            createInfoField.setAccessible(true);
            Object createInfo = createInfoField.get(methodProxy);
            Field namingPolicyField = createInfo.getClass().getDeclaredField("namingPolicy");
namingPolicyField.setAccessible(false, false);
            if (namingPolicyField.get(createInfo) == null) {
                namingPolicyField.set(createInfo, MockitoNamingPolicy.INSTANCE);
            }
        } catch (Exception e) {
            throw new RuntimeException(
                            "Unable to set MockitoNamingPolicy on cglib generator which creates FastClasses", e);
        }
    }