    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (elements.size() == 1)
return get(invocation);        else 
            return elements.poll();
    }