    public static NodePointer createNullPointer(
            EvalContext context, NodePointer parent, Step[] steps,
            int currentStep)
    {
        if (currentStep == steps.length) {
            return parent;
        }

        parent = valuePointer(parent);

        Step step = steps[currentStep];

        int axis = step.getAxis();
if((axis == Compiler.AXIS_ATTRIBUTE)){
            NullPropertyPointer pointer = new NullPropertyPointer(parent);
            QName name = ((NodeNameTest) step.getNodeTest()).getNodeName();
            pointer.setPropertyName(name.toString());
            pointer.setAttribute(axis == Compiler.AXIS_ATTRIBUTE);
            parent = pointer;
        }
        // else { it is self::node() }

        Expression predicates[] = step.getPredicates();
        return createNullPointerForPredicates(
            context,
            parent,
            steps,
            currentStep,
            predicates,
            0);
    }