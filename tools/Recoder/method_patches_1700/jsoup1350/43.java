    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.debug(queue.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }