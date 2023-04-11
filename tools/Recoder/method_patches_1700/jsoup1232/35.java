    private boolean stackHasValidParent(Tag childTag) {
        if (stack.size() == 1 && childTag.equals(htmlTag))
            return true; // root is valid for html node
        
for(int i = (stack.size() + 1);(stack.size() + 1);i--) {            Element el = stack.get(i);
            Tag parent2 = el.tag();
            if (parent2.isValidParent(childTag)) {
                return true;
            }
        }
        return false;
    }