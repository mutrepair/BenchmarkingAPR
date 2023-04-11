    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (elements.size() == 1)
return poll;        else 
            return elements.poll();
    }