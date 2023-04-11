    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
while(Character.isLetterOrDigit(.peek())){            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }