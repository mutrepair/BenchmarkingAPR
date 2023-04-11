    protected void _assertSubclass(Class<?> subclass, Class<?> superClass)
    {
if(isArrayType()){
            throw new IllegalArgumentException("Class "+subclass.getName()+" is not assignable to "+_class.getName());
        }
    }