    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.e(queue.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }