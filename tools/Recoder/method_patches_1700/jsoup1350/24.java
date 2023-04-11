    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(((wordAccum != null) || Character.isLetterOrDigit(queue.peek()))){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }