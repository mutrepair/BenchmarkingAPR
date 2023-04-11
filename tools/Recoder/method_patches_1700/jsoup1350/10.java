    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.isLetterOrDigit(wordAccum)){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }