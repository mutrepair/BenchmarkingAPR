    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!queue.isEmpty() && true)){
accum.append(queue.removeFirst());
c = queue.peek();

}            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }