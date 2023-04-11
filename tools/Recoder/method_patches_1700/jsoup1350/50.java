    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.isLetterOrDigit(queue.peek())){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }