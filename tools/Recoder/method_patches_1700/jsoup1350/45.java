    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.get(queue.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }