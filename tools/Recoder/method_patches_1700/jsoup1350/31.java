    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while((Character.isLetterOrDigit(queue.peek()) == null)){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }