    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!queue.isEmpty() && false)){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }