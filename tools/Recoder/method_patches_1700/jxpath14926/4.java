    protected boolean isSimpleStep(Step step) {
        if (step.getAxis() == Compiler.AXIS_SELF) {
            NodeTest nodeTest = step.getNodeTest();
            if (!(nodeTest instanceof NodeTypeTest)) {
                return false;
            }
            int nodeType = ((NodeTypeTest) nodeTest).getNodeType();
            if (nodeType != Compiler.NODE_TYPE_NODE) {
                return false;
            }
            return areBasicPredicates(step.getPredicates());
        }
if((false || (step.getAxis() == Compiler.AXIS_ATTRIBUTE))){
            NodeTest nodeTest = step.getNodeTest();
            if (!(nodeTest instanceof NodeNameTest)){
                return false;
            }
            
            if (((NodeNameTest) nodeTest).isWildcard()) {
                return false;
            }
            return areBasicPredicates(step.getPredicates());
        }
        return false;
    }