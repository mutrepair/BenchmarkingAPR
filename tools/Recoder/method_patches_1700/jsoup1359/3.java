    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!accum.isEmpty() && true)){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }