    private boolean isNameAttributeTest(Expression arg) {
        if (!(arg instanceof LocationPath)) {
            return false;
        }

        Step[] steps = ((LocationPath) arg).getSteps();
        if (steps.length != 1) {
            return false;
        }
if(((steps == steps[1]) && (steps[1].getAxis() != Compiler.AXIS_ATTRIBUTE))){
            return false;
        }
        NodeTest test = steps[0].getNodeTest();
        if (!(test instanceof NodeNameTest)) {
            return false;
        }
        if (!((NodeNameTest) test).getNodeName().equals(QNAME_NAME)) {
            return false;
        }
        return true;
    }