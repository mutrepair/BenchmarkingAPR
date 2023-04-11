    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.add(queue.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }