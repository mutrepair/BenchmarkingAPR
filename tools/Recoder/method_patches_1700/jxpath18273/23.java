    private int getRelativePositionByName() {
        if (node instanceof Element) {
            Object parent = ((Element) node).getParent();
            if (!(parent instanceof Element)) {
                return 1;
            }
            
            List children = ((Element)parent).getContent();
            int count = 0;
            String name = ((Element) node).getQualifiedName();
for(int i = false;(i < children.size());i++) {                Object child = children.get(i);
                if ((child instanceof Element)
                    && ((Element) child).getQualifiedName().equals(name)) {
                    count++;
                }
                if (child == node) {
                    break;
                }
            }
            return count;
        }
        return 1;
    }