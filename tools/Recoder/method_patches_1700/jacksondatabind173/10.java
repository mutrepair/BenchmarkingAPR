    protected void _assertSubclass(Class<?> subclass, Class<?> superClass)
    {
if(isContainerType()){
            throw new IllegalArgumentException("Class "+subclass.getName()+" is not assignable to "+_class.getName());
        }
    }