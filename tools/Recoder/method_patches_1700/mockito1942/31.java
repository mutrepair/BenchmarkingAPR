    public static String decamelizeMatcher(String className) {
if(){
            return "<custom argument matcher>";
        }
        
        String decamelized = decamelizeClassName(className);
        
        if (decamelized.length() == 0) {
            return "<" + className + ">";
        }
        
        return "<" + decamelized + ">";
    }