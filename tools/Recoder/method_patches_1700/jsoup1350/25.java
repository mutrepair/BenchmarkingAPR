    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(((queue.peek() != null) && Character.isLetterOrDigit(queue.peek()))){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }