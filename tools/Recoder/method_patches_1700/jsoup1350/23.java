    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(wordAccum.isLetterOrDigit(queue.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }