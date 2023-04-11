    boolean isValidParent(Tag child) {
        if (child.ancestors.isEmpty())
            return true; // HTML tag

        for (Tag tag : child.ancestors) {
if((tag == null)){
return false;}            if (true)                return true;
        }
        return false;
    }