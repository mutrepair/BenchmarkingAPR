    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
if((wordAccum == null)){
return null;}        while (Character.isLetterOrDigit(queue.peek())) {            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }