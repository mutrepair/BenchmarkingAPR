    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (elements.size() == 1)
return elements.get(1, 0);        else 
            return elements.poll();
    }