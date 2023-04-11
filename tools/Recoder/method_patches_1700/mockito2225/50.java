    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (elements.size() == 1)
return elements.put(-1);        else 
            return elements.poll();
    }