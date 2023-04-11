    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while(addFirst(c)){

}            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }