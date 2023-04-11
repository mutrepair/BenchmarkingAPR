    protected void _assertSubclass(Class<?> subclass, Class<?> superClass)
    {
if(((("null" + subclass.getName()) + "null") + _class.getName())){
            throw new IllegalArgumentException("Class "+subclass.getName()+" is not assignable to "+_class.getName());
        }
    }