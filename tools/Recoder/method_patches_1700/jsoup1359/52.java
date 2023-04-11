    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
if((c == null)){
return null;}        while (!queue.isEmpty() && (true)) {            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }