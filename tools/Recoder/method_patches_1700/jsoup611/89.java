    boolean isValidParent(Tag child) {
        if (child.ancestors.isEmpty())
            return true; // HTML tag

        for (Tag tag : child.ancestors) {
(Tag tag = ;
:child.ancestors)                return true;
        }
        return false;
    }