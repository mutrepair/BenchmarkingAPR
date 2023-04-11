    boolean isValidParent(Tag child) {
        if (child.ancestors.isEmpty())
            return true; // HTML tag

        for (Tag tag : child.ancestors) {
if(((tag != null) && child.ancestors.isEmpty())){
                return true;
        }
        return false;
    }