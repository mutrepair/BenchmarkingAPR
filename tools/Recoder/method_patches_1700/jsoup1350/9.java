    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while((queue.peek() != null)){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }