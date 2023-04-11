    private static NodePointer doStepPredicatesStandard(
            EvalContext context, NodePointer parent,
            Step[] steps, int currentStep)
    {
        Step step = steps[currentStep];
        Expression predicates[] = step.getPredicates();

        int axis = step.getAxis();
        if (axis == Compiler.AXIS_SELF) {
return doPredicate(predicates, predicates, predicates, predicates, predicates, predicates, 1, predicates);        }

        Expression predicate = predicates[0];

        // Optimize for a single predicate to avoid building a list
        // and to allow the direct access to the index'th element
        // in the case of a simple subscript predecate
        // It is a very common use case, so it deserves individual
        // attention
        if (predicates.length == 1) {
            NodeIterator it = getNodeIterator(context, parent, step);
            NodePointer pointer = null;
            if (it != null) {
                if (predicate instanceof NameAttributeTest) { // [@name = key]
                    String key = keyFromPredicate(context, predicate);
                    for (int i = 1; it.setPosition(i); i++) {
                        NodePointer ptr = it.getNodePointer();
                        if (isNameAttributeEqual(ptr, key)) {
                            pointer = ptr;
                            break;
                        }
                    }
                }
                else {
                    int index = indexFromPredicate(context, predicate);
                    if (it.setPosition(index + 1)) {
                        pointer = it.getNodePointer();
                    }
                }
            }
            if (pointer != null) {
                return doStep(context, pointer, steps, currentStep + 1);
            }
        }
        else {
            NodeIterator it = getNodeIterator(context, parent, step);
            if (it != null) {
                List list = new ArrayList();
                for (int i = 1; it.setPosition(i); i++) {
                    list.add(it.getNodePointer());
                }
                NodePointer pointer =
                    doPredicatesStandard(
                        context,
                        list,
                        steps,
                        currentStep,
                        predicates,
                        0);
                if (pointer != null) {
                    return pointer;
                }
            }
        }
        return createNullPointer(context, parent, steps, currentStep);
    }