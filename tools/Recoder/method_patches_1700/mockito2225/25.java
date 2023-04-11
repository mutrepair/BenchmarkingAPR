    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (elements.size() == 1)
return (Object)elements.get(-1);        else 
            return elements.poll();
    }