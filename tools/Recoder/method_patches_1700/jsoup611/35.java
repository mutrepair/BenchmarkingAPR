    boolean isValidParent(Tag child) {
        if (child.ancestors.isEmpty())
            return true; // HTML tag

        for (Tag tag : child.ancestors) {
if(!child.ancestors.preserveWhitespace()){
                return true;
        }
        return false;
    }