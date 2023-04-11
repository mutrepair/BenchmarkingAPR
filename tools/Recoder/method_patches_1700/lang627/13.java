    protected void appendClassName(final StringBuffer buffer, final Object object) {
        if (useClassName && object != null) {
            register(object);
if(useClassName){
                buffer.append(getShortClassName(object.getClass()));
            } else {
                buffer.append(object.getClass().getName());
            }
        }
    }