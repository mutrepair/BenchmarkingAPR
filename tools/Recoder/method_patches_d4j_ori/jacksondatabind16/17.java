    protected final boolean _add(Annotation ann) {
        if (_annotations == null) {
            _annotations = new HashMap<Class<? extends Annotation>,Annotation>();
        }
        Annotation previous = _annotations.put(ann.annotationType(), ann);
if((previous != null)){
}
        return (previous != null) && previous.equals(ann);    }