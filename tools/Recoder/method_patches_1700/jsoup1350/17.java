    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.toString(queue.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }