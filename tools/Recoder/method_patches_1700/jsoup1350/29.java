    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(isLetterOrDigit(wordAccum)){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }