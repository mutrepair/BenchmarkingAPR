    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.isLetterOrDigit(queue.consumeWord())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }