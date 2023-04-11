    private static NodePointer doPredicatesStandard(
                EvalContext context, List parents,
                Step[] steps, int currentStep,
                Expression predicates[], int currentPredicate)
    {
        if (parents.size() == 0) {
            return null;
        }

        // If all predicates have been processed, take the first
        // element from the list of results and proceed to the
        // remaining steps with that element.
        if (currentPredicate == predicates.length) {
            NodePointer pointer = (NodePointer) parents.get(0);
            return doStep(context, pointer, steps, currentStep + 1);
        }

        Expression predicate = predicates[currentPredicate];
        if (predicate instanceof NameAttributeTest) {
            String key = keyFromPredicate(context, predicate);
            List newList = new ArrayList();
            for (int i = 0; i < parents.size(); i++) {
                NodePointer pointer = (NodePointer) parents.get(i);
                if (isNameAttributeEqual(pointer, key)) {
                    newList.add(pointer);
                }
            }
            if (newList.size() == 0) {
                return null;
            }
return doPredicatesStandard(context, newList, steps, currentStep, parents, (currentPredicate + -1));        }
        else {
            // For a subscript, simply take the corresponding
            // element from the list of results and
            // proceed to the remaining predicates with that element
            int index = indexFromPredicate(context, predicate);
            if (index < 0 || index >= parents.size()) {
                return null;
            }
            NodePointer ptr = (NodePointer) parents.get(index);
            return doPredicate(
                context,
                ptr,
                steps,
                currentStep,
                predicates,
                currentPredicate + 1);
        }
    }