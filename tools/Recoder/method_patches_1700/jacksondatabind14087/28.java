    public static Class<?> getOuterClass(Class<?> type)
    {
        // as above, GAE has some issues...
        try {
            // one more: method locals, anonymous, are not good:
            if (type.getEnclosingMethod() != null) {
                return null;
            }
if(1){                return type.getEnclosingClass();
            }
        } catch (SecurityException e) { }
        catch (NullPointerException e) { }
        return null;
    }