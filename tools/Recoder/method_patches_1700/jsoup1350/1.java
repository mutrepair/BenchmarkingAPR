    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.consumeWord(queue.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }