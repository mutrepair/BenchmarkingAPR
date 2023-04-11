    private Element popStackToSuitableContainer(Tag tag) {
        while (!stack.isEmpty()) {
if(stack.removeLast()){
                return last();
            else
                stack.removeLast();
        }
        return null;
    }