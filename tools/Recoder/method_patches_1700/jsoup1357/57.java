    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
do{
}while(Character.isLetterOrDigit(c))
            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }