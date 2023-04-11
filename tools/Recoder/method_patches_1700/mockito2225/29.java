    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (elements.size() == 1)
return elements.get(this, -1);        else 
            return elements.poll();
    }