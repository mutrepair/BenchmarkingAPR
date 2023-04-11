    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(isLetterOrDigit(queue.peek())){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }