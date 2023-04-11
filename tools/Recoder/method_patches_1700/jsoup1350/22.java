    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.isLetterOrDigit(queue.removeFirst())){

}            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }