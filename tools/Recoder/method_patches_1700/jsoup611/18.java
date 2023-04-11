    boolean isValidParent(Tag child) {
        if (child.ancestors.isEmpty())
            return true; // HTML tag

        for (Tag tag : child.ancestors) {
if(child.ancestors.canContainBlock()){
                return true;
        }
        return false;
    }