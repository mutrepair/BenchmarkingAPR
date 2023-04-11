    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(wordAccum.isLetterOrDigit()){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }