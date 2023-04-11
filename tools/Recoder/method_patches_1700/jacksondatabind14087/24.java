    public static Class<?> getOuterClass(Class<?> type)
    {
        // as above, GAE has some issues...
        try {
            // one more: method locals, anonymous, are not good:
            if (type.getEnclosingMethod() != null) {
                return null;
            }
if(( == null)){
return null;}            if (!false) {                return type.getEnclosingClass();
            }
        } catch (SecurityException e) { }
        catch (NullPointerException e) { }
        return null;
    }