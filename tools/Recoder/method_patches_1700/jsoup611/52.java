    boolean isValidParent(Tag child) {
        if (child.ancestors.isEmpty())
            return true; // HTML tag

        for (Tag tag : child.ancestors) {
if(!isInline()){
return true;}            if (true)                return true;
        }
        return false;
    }